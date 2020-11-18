package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import com.example.demo.utils.LogUtils;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 传统日志测试
 * --------------------------------------
 * @ClassName: LogController.java
 * @Date: 2020/11/2 15:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@RestController
public class LogController {

    @RequestMapping("/logTest")
    @MethodLog
    public void logTest() {
        Logger log = LogUtils.getLogger();
        log.info("log...一般日志测试");

        Logger exceptionLog = LogUtils.getExceptionLogger();
        exceptionLog.error("exceptionLog...错误日志测试");
    }

}
