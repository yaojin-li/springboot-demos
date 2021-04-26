package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试redis数据源
 * --------------------------------------
 * @ClassName: SigleController2.java
 * @Date: 2021/4/26 19:33
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class SigleController2 {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/redis")
    public void defaultTest() {
        redisTemplate.opsForValue().set("Single", "测试Single");
        String test = redisTemplate.opsForValue().get("Single").toString();
        System.out.println(test);
    }
}
