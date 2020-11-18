package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import com.example.demo.utils.LogUtils;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: aop 日志测试
 * --------------------------------------
 * @ClassName: LogAopController.java
 * @Date: 2020/11/18 14:01
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@RestController
public class LogAopController {

    /**
     * http://localhost:8080/logAopTest?name=test
     **/
    @RequestMapping("/logAopTest")
    @MethodLog
    public String logAopTest(@RequestParam String name) {
        Logger log = LogUtils.getLogger();
        log.info(name);
        log.info("log aop 测试。。");

        Logger exceptionLog = LogUtils.getExceptionLogger();
        exceptionLog.error("exceptionLog...错误日志测试");
        return name;
    }
}
