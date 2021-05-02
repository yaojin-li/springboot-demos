//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.StringUtils;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * @Description: redis多数据源配置文件
// * 对应配置文件中多个redis配置。
// * --------------------------------------
// * @ClassName: MultiRedisConfig.java
// * @Date: 2020/10/26 22:57
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Configuration
//public class MultiRedisConfig {
//    /**
//     * default
//     */
//    @Value("${spring.redis.default.host}")
//    private String defaultHost;
//
//    @Value("${spring.redis.default.port}")
//    private String defaultPort;
//
//    @Value("${spring.redis.default.password}")
//    private String defaultPassword;
//
//    /**
//     * localhost
//     */
//    @Value("${spring.redis.localhost.host}")
//    private String localhostHost;
//
//    @Value("${spring.redis.localhost.port}")
//    private String localhostPort;
//
//    @Value("${spring.redis.localhost.password}")
//    private String localhostPassword;
//
//    private static final int MAX_IDLE = 200; //最大空闲连接数
//    private static final int MAX_TOTAL = 1024; //最大连接数
//    private static final long MAX_WAIT_MILLIS = 10000; //建立连接最长等待时间
//
//
//    //配置工厂
//    public RedisConnectionFactory connectionFactory(String host, int port, String password, int maxIdle,
//                                                    int maxTotal, long maxWaitMillis, int index) {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(host);
//        jedisConnectionFactory.setPort(port);
//
//        if (!StringUtils.isEmpty(password)) {
//            jedisConnectionFactory.setPassword(password);
//        }
//
//        if (index != 0) {
//            jedisConnectionFactory.setDatabase(index);
//        }
//
//        jedisConnectionFactory.setPoolConfig(poolConfig(maxIdle, maxTotal, maxWaitMillis, false));
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//
//    //连接池配置
//    public JedisPoolConfig poolConfig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMaxTotal(maxTotal);
//        poolConfig.setMaxWaitMillis(maxWaitMillis);
//        poolConfig.setTestOnBorrow(testOnBorrow);
//        return poolConfig;
//    }
//
//
//    @Primary
//    @Bean(name = "defaultTemplate")
//    public RedisTemplate<String, Object> defaultTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(
//                connectionFactory(defaultHost, Integer.parseInt(defaultPort), defaultPassword, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, 0));
//        return template;
//    }
//
//    @Bean(name = "localhostTemplate")
//    public RedisTemplate<String, Object> localhostTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(
//                connectionFactory(localhostHost, Integer.parseInt(localhostPort), localhostPassword, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, 0));
//        return template;
//    }
//}