package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: DockerController.java
 * @Date: 2021/5/2 15:47
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class DockerController {

    @Value("${sys.app}")
    private String app;


    @RequestMapping("/docker")
    public String docker() {
        System.out.println(app);
        return app;
    }

}
