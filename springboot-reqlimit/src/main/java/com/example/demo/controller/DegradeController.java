package com.example.demo.controller;

import com.example.demo.service.DegradeService;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    DegradeService degradeService;

    /**
     * 1.抛出异常的方式定义资源
     * 代码侵入性高
     */
    @RequestMapping(value = "/limit")
    public void limit() {
        try {
            degradeService.limit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.使用Sentinel注解实现熔断。类似限流。
     * 需要Sentinel切面配置
     */
    @GetMapping(value = "/query/{name}")
    public String query(@PathVariable String name) {
        return degradeService.degrade(name);
    }


}
