//package com.example.demo.config;
//
//import org.redisson.client.RedisException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * @Description: 基础redis配置
// * --------------------------------------
// * @ClassName: SingleRedisConfig.java
// * @Date: 2020/10/26 22:57
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//public class SingleRedisConfig {
//    private static final Logger logger = LoggerFactory.getLogger(SingleRedisConfig.class);
//
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private Integer port;
//    @Value("${spring.redis.database}")
//    private Integer database;
//
//    @Value("${spring.redis.lettuce.pool.max-active}")
//    private Integer maxActive;
//    @Value("${spring.redis.lettuce.pool.max-wait}")
//    private Integer maxWait;
//    @Value("${spring.redis.lettuce.pool.max-idle}")
//    private Integer maxIdle;
//    @Value("${spring.redis.lettuce.pool.min-idle}")
//    private Integer minIdle;
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
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName(host);
//        configuration.setPort(port);
//        configuration.setDatabase(database);
//        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettuceClientConfiguration.builder();
//        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, builder.build());
//        return factory;
//    }
//
//    @Bean
//    public JedisPool redisPoolFactory(){
//        JedisPool jedisPool = null;
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxIdle(maxIdle);
//            config.setMinIdle(minIdle);
//            config.setMaxWaitMillis(maxWait);
//            config.setMaxTotal(maxActive);
//            jedisPool = new JedisPool(config, host, port);
//        }catch (Exception e){
//            throw new RedisException("JedisPool初始化异常！");
//        }
//        return jedisPool;
//    }
//
//}
