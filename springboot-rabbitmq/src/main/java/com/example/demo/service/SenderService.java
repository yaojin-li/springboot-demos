package com.example.demo.service;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.utils.ObjUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void send(Object message){
        String msg = ObjUtil.beanToString(message);
        amqpTemplate.convertAndSend(RabbitMqConfig.TEST_QUEUQ, msg);

    }


}
