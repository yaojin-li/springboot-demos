package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: --------------------------------------
 * @ClassName: RabbitMqConfig.java
 * @Date: 2021/3/13 17:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class RabbitMqConfig {
    /**
     * 测试
     * */
    public static final String TEST_QUEUQ = "test.queue";


    /**
     * Direct模式 交换机 Exchange
     * */
    @Bean
    public Queue queue(){
        // 创建持久性消息队列
        return new Queue(TEST_QUEUQ, true);
    }



}




