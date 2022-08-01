package com.example.demo.service;

import com.example.demo.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: ReceiverService.java
 * @Date: 2021/3/13 17:22
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
@Slf4j
public class ReceiverService {

    /**
     * 基本接收
     */
    @RabbitListener(queues = RabbitMqConfig.TEST_QUEUQ)
    public void receive(String message) {
        log.info("接收消息：" + message);
    }


    /**
     * topic接收
     */
    @RabbitListener(queues = RabbitMqConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info("topic1接收消息：" + message);
    }

    @RabbitListener(queues = RabbitMqConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        log.info("topic2接收消息：" + message);
    }


    /**
     * fanout接收
     */
    @RabbitListener(queues = RabbitMqConfig.FANOUT_QUEUE)
    public void receiveFanout(String message) {
        log.info("fanout接收消息：" + message);
    }


    /**
     * header 接收
     */
    @RabbitListener(queues = RabbitMqConfig.HEADER_QUEUE)
    public void receiveHeader(String message) {
        log.info("header接收消息：" + message);
    }


}



