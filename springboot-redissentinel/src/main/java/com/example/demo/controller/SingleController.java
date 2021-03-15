package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: SingleController.java
 * @Date: 2021/3/15 23:52
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class SingleController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/singleTest")
    public void demo() {
        redisTemplate.opsForValue().set("Single", "测试Single");
        String test = redisTemplate.opsForValue().get("Single").toString();
        System.out.println(test);
    }
}
