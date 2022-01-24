//package com.example.demo.sintinel;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Description: redis哨兵模式配置
// * --------------------------------------
// * @ClassName: SentinelConfig.java
// * @Date: 2021/12/22 0022  10:46
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//public class SentinelConfig {
//
//    @Value("${spring.redis.sentinel.nodes}")
//    private String sentinelNodes;
//    @Value("${spring.redis.sentinel.master}")
//    private String sentinelMaster;
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${spring.redis.lettuce.pool.max-active}")
//    private Integer maxActive;
//    @Value("${spring.redis.lettuce.pool.max-idle}")
//    private Integer maxIdle;
//    @Value("${spring.redis.lettuce.pool.min-idle}")
//    private Integer minIdle;
//    @Value("${spring.redis.lettuce.pool.max-wait}")
//    private Long maxWait;
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new StringRedisSerializer());
//        return template;
//    }
//
//    @Bean
//    LettuceConnectionFactory lettuceConnectionFactory() {
//        // 连接池配置
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxIdle(maxIdle == null ? 8 : maxIdle);
//        poolConfig.setMinIdle(minIdle == null ? 1 : minIdle);
//        poolConfig.setMaxTotal(maxActive == null ? 8 : maxActive);
//        poolConfig.setMaxWaitMillis(maxWait == null ? 5000L : maxWait);
//        LettucePoolingClientConfiguration.builder()
//                .poolConfig(poolConfig)
//                .build();
//
//        // redis集群
//        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
//        Set<RedisNode> nodes = new HashSet<>();
//        String[] sentinels = sentinelNodes.split(",");
//        for (String sentinel : sentinels) {
//            if (StringUtils.isNotEmpty(sentinel)) {
//                String[] info = sentinel.split(":");
//                nodes.add(new RedisNode(info[0], Integer.parseInt(info[1])));
//            }
//        }
//        configuration.setSentinels(nodes);
//        configuration.setMaster(sentinelMaster);
//        configuration.setPassword(password);
//        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettuceClientConfiguration.builder();
//        return new LettuceConnectionFactory(configuration, builder.build());
//    }
//
//}
