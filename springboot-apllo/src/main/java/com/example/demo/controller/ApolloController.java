package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: --------------------------------------
 * @ClassName: AplloController.java
 * @Date: 2021/4/19 18:43
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class AplloController {
    private static Logger logger = LoggerFactory.getLogger(AplloController.class);

    @Value("${server.port}")
    String port;

    @GetMapping("test")
    public String test(String name) {
        logger.debug("debug log....");
        logger.info("info log....");
        logger.warn("warn log....");
        return "name is " + name + ", port is " + port;
    }

}
