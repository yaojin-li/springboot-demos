package com.example.demo.controller;

import com.example.demo.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: MqController.java
 * @Date: 2021/3/13 17:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class MqController {

    @Autowired
    public SenderService senderService;

    @RequestMapping("/testMq")
    public void testMq() {
        senderService.send("测试消息队列 RabbitMQ...");
    }

}
