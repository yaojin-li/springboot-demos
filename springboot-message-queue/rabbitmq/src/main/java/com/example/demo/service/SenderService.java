package com.example.demo.service;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.utils.ObjUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;

/**
 * @Description: --------------------------------------
 * @ClassName: SenderService.java
 * @Date: 2021/3/13 17:21
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
@Slf4j
public class SenderService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 基础测试
     */
    public void send(Object message) {
        String msg = ObjUtil.beanToString(message);
        log.info("基础测试-发送消息：" + message);
        amqpTemplate.convertAndSend(RabbitMqConfig.TEST_QUEUQ, msg);
    }


    /**
     * topic 模式
     */
    public void sendTopic(Object message) {
        String msg = ObjUtil.beanToString(message);
        log.info("topic 测试-发送消息：" + message);
        amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "topic.key1", msg + "1");
        amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "topic.key2", msg + "2");
    }


    /**
     * fanout 模式
     */
    public void sendFanout(Object message) {
        String msg = ObjUtil.beanToString(message);
        log.info("fanout 测试-发送消息：" + message);
        amqpTemplate.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "", msg);
    }


    /**
     * header 模式
     */
    public void sendHeader(Object message) {
        String msg = ObjUtil.beanToString(message);
        log.info("header 测试-发送消息：" + message);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1", "value1");
        properties.setHeader("header2", "value2");
        Message obj = new Message(msg.getBytes(), properties);
        amqpTemplate.convertAndSend(RabbitMqConfig.HEADERS_EXCHANGE, "", obj);
    }


}
