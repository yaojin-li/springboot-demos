package com.example.demo.config;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @Description: --------------------------------------
 * @ClassName: RedisConfig.java
 * @Date: 2020/10/26 22:57
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class MultiRedisConfig2 {

    /**
     * default 系统redis
     */
    @Value("${spring.redis.password}")
    private String defaultPassword;
    /**
     * 主节点名字
     */
    @Value("${spring.redis.sentinel.master}")
    private String master;
    /**
     * 哨兵节点配置，数据格式 127.0.0.1:26379,127.0.0.1:26380
     */
    @Value("${spring.redis.sentinel.nodes}")
    private String sentinelNodes;


    /**
     * 校验token的redis1
     */
    @Value("${spring.redis.checkOne.host}")
    private String checkOneHost;

    @Value("${spring.redis.checkOne.port}")
    private String checkOnePort;

    @Value("${spring.redis.checkOne.password}")
    private String checkOnePassword;

    /**
     * 校验token的redis2
     */
    @Value("${spring.redis.checkTwo.host}")
    private String checkTwoHost;

    @Value("${spring.redis.checkTwo.port}")
    private String checkTwoPort;

    @Value("${spring.redis.checkTwo.password}")
    private String checkTwoPassword;


    private static final int MAX_IDLE = 200; //最大空闲连接数
    private static final int MAX_TOTAL = 1024; //最大连接数
    private static final long MAX_WAIT_MILLIS = 10000; //建立连接最长等待时间


    //配置工厂
    public RedisConnectionFactory connectionFactory(String host, int port, String password, int maxIdle,
                                                    int maxTotal, long maxWaitMillis, int index) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);

        if (!StringUtils.isEmpty(password)) {
            jedisConnectionFactory.setPassword(password);
        }

        if (index != 0) {
            jedisConnectionFactory.setDatabase(index);
        }

        jedisConnectionFactory.setPoolConfig(poolConfig(maxIdle, maxTotal, maxWaitMillis, false));
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    //连接池配置
    public JedisPoolConfig poolConfig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }


    /**
     * redis哨兵连接池工厂
     * 创建条件：
     * 1、系统中没有任何RedisConnectionFactory对象
     * 2、需要存在JedisPoolConfig连接池配置时，才会创建此对象
     *
     * @return 连接池工厂
     */
    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    @ConditionalOnClass(JedisPoolConfig.class)
    public RedisConnectionFactory redisConnectionFactory() {
        // 装配哨兵节点配置文件
        if (StringUtils.isBlank(sentinelNodes)) {
            throw new RuntimeException("哨兵服务ip&port:不能为空");
        }

        // 哨兵专用配置类
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
        // 哨兵节点配置
        sentinelConfig.setSentinels(getRedisNode(sentinelNodes));
        // 主节点配置
        sentinelConfig.setMaster(master);
        JedisConnectionFactory factory = new JedisConnectionFactory(sentinelConfig);
        // 配置链接哨兵集群的密码
        factory.setPassword(defaultPassword);
        return factory;
    }


    /**
     * 解析哨兵节点配置
     */
    private Iterable<RedisNode> getRedisNode(String sentinelNodes) {
        String[] urls = StringUtils.split(sentinelNodes, ",");
        Set<RedisNode> nodes = Sets.newHashSetWithExpectedSize(urls.length);
        for (String url : urls) {
            String[] splits = url.split(":");
            nodes.add(new RedisNode(splits[0], Integer.parseInt(splits[1])));
        }
        return nodes;
    }


    /**
     * 默认应用中的redis服务
     */
    @Primary
    @Bean(name = "defaultTemplate")
    public RedisTemplate redisDefaultTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * 南方中心机房redis服务
     */
    @Bean(name = "checkOneTemplate")
    public StringRedisTemplate checkOneTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(
                connectionFactory(checkOneHost, Integer.parseInt(checkOnePort),
                        checkOnePassword, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, 0));
        return template;
    }

    /**
     * 张江机房redis服务
     */
    @Bean(name = "checkTwoTemplate")
    public StringRedisTemplate checkTwoTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(
                connectionFactory(checkTwoHost, Integer.parseInt(checkTwoPort),
                        checkTwoPassword, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, 0));
        return template;
    }

}