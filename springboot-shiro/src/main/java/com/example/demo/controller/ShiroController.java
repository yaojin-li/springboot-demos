package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: ShiroController.java
 * @Date: 2021/1/26 19:21
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class ShiroController {

    @GetMapping("/shiroTest")
    public String shiroTest(){
        return "";
    }

}
