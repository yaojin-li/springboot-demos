//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @Description: 测试redis多数据源
// * 对应配置文件中多个redis配置
// * --------------------------------------
// * @ClassName: SingleController.java
// * @Date: 2021/3/15 23:52
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@RestController
//public class SingleController {
//
//    @Autowired
//    @Resource(name = "defaultTemplate")
//    private RedisTemplate<String, Object> defaultTemplate;
//
//    @Autowired
//    @Resource(name = "localhostTemplate")
//    private RedisTemplate<String, Object> localhostTemplate;
//
//    @RequestMapping("/defaultTest")
//    public void defaultTest() {
//        defaultTemplate.opsForValue().set("Single", "测试Single");
//        String test = defaultTemplate.opsForValue().get("Single").toString();
//        System.out.println(test);
//    }
//
//    @RequestMapping("/otherRedisTest")
//    public void otherRedisTest() {
//        localhostTemplate.opsForValue().set("otherSingle", "other redis Single");
//        String test = localhostTemplate.opsForValue().get("otherSingle").toString();
//        System.out.println(test);
//    }
//
//}
