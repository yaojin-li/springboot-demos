package com.example.demo.service;

import com.example.demo.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * --------------------------------------
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

    @RabbitListener(queues = RabbitMqConfig.TEST_QUEUQ)
    public void receive(String message){
        log.info("接收消息：" + message);
    }

}
