package com.example.demo.controller;

import com.example.demo.annotation.RequestLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 请求限流测试
 * 1. 使用注解+拦截器方式实现
 * --------------------------------------
 * @ClassName: AppController.java
 * @Date: 2021/3/22 15:38
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 一秒内请求超过三次，拦截限流
     */
    @RequestMapping("/testLimit")
    @RequestLimit(seconds = 1, maxCount = 3)
    public void testLimit() {
        redisTemplate.opsForValue().set("test", "just for test");
        System.out.println(redisTemplate.opsForValue().get("test"));
    }
}
