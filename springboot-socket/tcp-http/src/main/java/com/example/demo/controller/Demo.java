package com.example.demo.controller;

import com.example.demo.server.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: Demo.java
 * @Date: 2022/2/25 0025  17:48
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    @RequestMapping("testTcp")
    public byte[] testTcp(){
        LOGGER.info("进入testTcp...");
        return Base64Utils.encode("http请求返回测试。".getBytes());
    }

}
