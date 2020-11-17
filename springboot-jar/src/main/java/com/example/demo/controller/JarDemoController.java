package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: JarDemoController.java
 * @Date: 2020/11/16 19:35
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class JarDemoController {

    @RequestMapping("/jarDemo")
    public String jarDemo(@RequestParam String name){
        System.out.println("进入 jar demo 方法...");
        System.out.println(String.format("name: %s", name));
        return name;
    }

}
