//package com.example.demo.integration.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.integration.redis.util.RedisLockRegistry;
//
///**
// * @Description: 基于redis使用Spring Integration实现分布式锁
// * --------------------------------------
// * @ClassName: RedisLockConfiguration .java
// * @Date: 2021/12/27 0027  9:48
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Configuration
//public class RedisLockConfiguration {
//    @Bean
//    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisLockRegistry(redisConnectionFactory, "redis-lock");
//    }
//
//}
