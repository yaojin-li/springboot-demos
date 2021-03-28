package com.example.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 事务注解
 * */
@EnableTransactionManagement
@MapperScan("com.example.demo.base.dao")
// 排除原生Druid的快速配置类：DruidDataSourceAutoConfigure。让mybatis-plus提供的dynamic-datasource来完成配置
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
