package com.example.demo.sentinlImpl.controller;

import com.example.demo.sentinlImpl.service.DegradeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 熔断降级测试
 * --------------------------------------
 * @ClassName: DegradeController.java
 * @Date: 2021/4/17 17:29
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@Slf4j
@RequestMapping(value = "degrade")
public class DegradeController {
    private static final Logger log = LoggerFactory.getLogger(DegradeController.class);

    @Autowired
    DegradeService degradeService;

    /**
     * 使用Sentinel注解实现熔断。类似限流。
     * 需要Sentinel切面配置
     */
    @RequestMapping(value = "/{name}")
    public void query(@PathVariable String name) {
        while (true){
            log.info(degradeService.degrade(name));
        }
    }


}
