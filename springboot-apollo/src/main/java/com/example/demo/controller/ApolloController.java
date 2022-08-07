package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Apollo配置测试
 * 基于 @value 注解注入，非系统启动配置加载bean可实现热部署；
 * --------------------------------------
 * @ClassName: ApolloController.java
 * @Date: 2021/4/19 20:07
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class ApolloController {

    private static Logger logger = LoggerFactory.getLogger(ApolloController.class);

    /**
     * 基本配置测试
     */
    @Value("${server.id}")
    String serverId;
    /**
     * 默认配置测试
     */
    @Value("${test:默认值}")
    private String test;
    /**
     * namespace配置测试
     */
    @Value("${namespace.test}")
    String namespaceTest;

    @GetMapping("/testBase")
    public void testBase() {
        logger.info("serverId:{}", serverId);
        logger.info("test:{}", test);
        logger.info("namespace:{}", namespaceTest);
    }


    @Autowired
    String apolloBean;

    /**
     * 测试 Apollo bean 配置
     */
    @GetMapping("/testBeanConfig")
    public void testBeanConfig() {
        logger.info(apolloBean);
    }


    @Value("${system.orderConfig}")
    String orderConfig;
    /**
     * 测试配置文件与Apollo配置加载优先级
     */
    @GetMapping("/testLoadOrder")
    public void testLoadOrder() {
        logger.info(orderConfig);
    }

}