//package com.example.demo.cluster;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @Description: --------------------------------------
// * @ClassName: ClusterController.java
// * @Date: 2020/10/26 22:32
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@RestController
//public class ClusterController {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @RequestMapping("/cluster")
//    public void demo() {
//        redisTemplate.opsForValue().set("Cluster", "测试Cluster");
//        String test = redisTemplate.opsForValue().get("Cluster").toString();
//        System.out.println(test);
//    }
//}
