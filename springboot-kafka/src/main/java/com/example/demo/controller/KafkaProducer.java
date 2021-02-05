package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: KafkaProducer.java
 * @Date: 2020/11/26 19:00
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    /**
     * http://localhost:8095/sendMsg?topic=topicdemo&msg=测试demo
     * */
    @RequestMapping("/sendMsg")
    public String sendMsg(String topic, String msg){
        kafkaTemplate.send(topic, msg);
        return "success";
    }
}















