package com.example.controller;

import com.example.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: TestController.java
 * @Date: 2021/4/7 11:45
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class TestController {

    @RequestMapping("/model")
    public void model(){
        System.out.println("test controller...");
        TestService.testService();
    }

}
