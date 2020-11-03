package com.example.demo.controller;

import com.example.demo.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: demoController.java
 * @Date: 2020/11/2 15:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class Log4j2DemoController {

    @RequestMapping("/log4j2Test")
    public void demo() {
        Logger exceptionLog = LogUtils.getExceptionLogger();
        Logger bussinessLog = LogUtils.getBussinessLogger();
        Logger dbLog = LogUtils.getDBLogger();
        Logger platformLog = LogUtils.getPlatformLogger();

        exceptionLog.error("getExceptionLogger===错误日志测试");
        bussinessLog.info("getBussinessLogger===普通日志测试");
        dbLog.info("getDBLogger===调试日志测试");
        platformLog.warn("getPlatformLogger===警告日志测试");
    }
}
