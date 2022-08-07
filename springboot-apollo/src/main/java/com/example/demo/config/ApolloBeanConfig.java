package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 测试bean加载配置
 * --------------------------------------
 * @ClassName: ApolloBeanConfig.java
 * @Date: 2022/8/7 9:03
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class ApolloBeanConfig {

    @Value("${server.bean.config:bean from app}")
    public String beanConfig;

    @Bean(value = "apolloBean")
    public String apolloBean() {
        String result = this.beanConfig;
        return result + "...";
    }

}
