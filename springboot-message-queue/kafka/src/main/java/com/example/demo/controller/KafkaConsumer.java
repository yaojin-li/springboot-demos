package com.example.demo.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description: --------------------------------------
 * @ClassName: KafkaConsumer.java
 * @Date: 2020/11/26 17:02
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topicdemo")
    public void listen(ConsumerRecord record){
        System.out.println(record.topic() + ":" + record.value());
    }

}
