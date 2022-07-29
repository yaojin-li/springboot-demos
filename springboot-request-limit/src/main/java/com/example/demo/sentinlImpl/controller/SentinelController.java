package com.example.demo.sentinlImpl.controller;

import com.example.demo.sentinlImpl.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "sentinel")
public class SentinelController {

    @Autowired
    SentinelService sentinelService;

    /**
     * 使用Sentinel注解实现限流
     * 需要Sentinel切面配置
     */
    @RequestMapping(value = "/{name}")
    public String test(@PathVariable String name) {
        return sentinelService.test(name);
    }

}
