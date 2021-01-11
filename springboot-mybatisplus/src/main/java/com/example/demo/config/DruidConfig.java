package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: datasource数据源初始化
 * --------------------------------------
 * @ClassName: DruidConfig.java
 * @Date: 2020/11/5 10:59
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class DruidConfig {

    /**
     * 全局配置文件属性注入到 DruidDataSource 的同名参数中
     * */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource(){
        return new DruidDataSource();
    }

}
