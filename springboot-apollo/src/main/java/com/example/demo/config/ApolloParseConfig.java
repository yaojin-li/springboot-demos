package com.example.demo.config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: apollo配置解析
 * 项目启动加载apollo配置并解析到内存变量
 * --------------------------------------
 * @ClassName: ApolloParseConfig.java
 * @Date: 2022/7/28 21:35
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class ApolloParseConfig {
    private static final Logger logger = LoggerFactory.getLogger(ApolloParseConfig.class);
    private List<String> nums;

    @Value("${sms.error.number}")
    private String smsErrorNumber;

    /**
     * 启动时只加载一次
     * */
    @PostConstruct
    public void smsErrorNumberInit() {
        if (StringUtils.isBlank(this.smsErrorNumber)) {
            logger.error("Apollo配置项[sms.error.number]为空！");
            this.nums = new ArrayList<>();
        }
        logger.info("获取apollo配置参数[sms.error.number]：{}", smsErrorNumber);
        this.nums = Arrays.asList(this.smsErrorNumber.split(","));
    }

    public List<String> getNums() {
        return this.nums;
    }

}





