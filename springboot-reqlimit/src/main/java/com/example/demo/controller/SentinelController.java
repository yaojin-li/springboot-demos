package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 请求限流测试
 * 2. 使用sentinel组件
 * --------------------------------------
 * @ClassName: SentinelController.java
 * @Date: 2021/4/16 14:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@Slf4j
public class SentinelController {

    private static final String RESOURCE_NAME = "testLimit";

//    /**
//     *
//     */
//    @RequestMapping("/testLimit")
//    public String testLimit() {
////        if (isLimited()){
////            return "sentinel limited...";
////        }
//        return "sentinel allow...";
//    }

    @RequestMapping(value = "/testLimit2")
    @SentinelResource(value = "/testLimit2")
    public String testLimit2() {
        return "sentinel allow...";
    }

}
