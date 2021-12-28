package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

/**
 * @Description: --------------------------------------
 * @ClassName: JedisConfig.java
 * @Date: 2021/12/22 0022  16:05
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class JedisConfig {

    @Bean
    public Jedis getJedis() {
        return new Jedis(new HostAndPort("localhost", 6379));
    }


}