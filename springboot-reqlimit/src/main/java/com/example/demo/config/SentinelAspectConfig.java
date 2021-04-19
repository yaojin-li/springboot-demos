package com.example.demo.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;

/**
 * @Description: sentinel 切面配置类
 * --------------------------------------
 * @ClassName: SentinelAspectConfig.java
 * @Date: 2021/4/17 10:01
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class SentinelAspectConfig {

    @Bean
    SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }

}
