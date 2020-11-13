package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Log4j2 + lombok 输出日志
 * 注：需要安装 lombok 插件。
 * --------------------------------------
 * @ClassName: Log4j2Controller.java
 * @Date: 2020/11/12 19:13
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@Log4j2
public class Log4j2Controller {

    @MethodLog
    @RequestMapping("/log4j2Test")
    public void log4j2Test() {
        log.info("lombok log info...lombok 普通日志测试");
        log.error("lombok log error...lombok 错误日志测试");
    }

}
