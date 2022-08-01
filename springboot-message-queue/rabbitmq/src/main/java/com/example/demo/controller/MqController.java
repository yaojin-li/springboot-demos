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
@RequestMapping("/mq")
public class MqController {

    @Autowired
    public SenderService senderService;

    /**
     * 测试基本消息队列
     */
    @RequestMapping("/testMq")
    public void testMq() {
        senderService.send("测试消息队列 RabbitMQ...");
    }

    /**
     * 测试消息队列 topic
     */
    @RequestMapping("/mqTopic")
    public void testMqTopic() {
        senderService.sendTopic("测试topic消息队列 topic RabbitMQ");
    }

    /**
     * 测试消息队列 fanout
     */
    @RequestMapping("/mqFanout")
    public void testMqFanout() {
        senderService.sendFanout("测试fanout消息队列 fanout RabbitMQ");
    }

    /**
     * 测试消息队列 header
     */
    @RequestMapping("/mqHeader")
    public void testMqHeader() {
        senderService.sendHeader("测试header消息队列 header RabbitMQ");
    }

}







