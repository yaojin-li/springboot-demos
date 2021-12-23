package com.example.demo.sintinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: --------------------------------------
 * @ClassName: SentinelController.java
 * @Date: 2020/10/26 22:32
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class SentinelController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/sentinel")
    public void demo() {
        redisTemplate.opsForValue().set("Sentinel", "测试Sentinel");
        String test = redisTemplate.opsForValue().get("Sentinel").toString();
        System.out.println(test);
    }
}
