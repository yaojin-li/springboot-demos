package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: ApolloController.java
 * @Date: 2021/4/19 20:07
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class ApolloController {

    private static Logger logger = LoggerFactory.getLogger(ApolloController.class);

    @Value("${server.port}")
    String port;

    @GetMapping("test")
    public String test(String name) {

        logger.debug("debug log...");
        logger.info("info log...");
        logger.warn("warn log...");

        return "hi " + name + " ,i am from port:" + port;
    }


    @Value("${test:默认值}")
    private String test;

    @GetMapping("/test1")
    public String test1(){
        return "test的值为:" + test;
    }

}