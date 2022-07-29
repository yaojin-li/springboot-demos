package com.example.demo.redisImpl.config;

import com.example.demo.redisImpl.intercept.RequestLimitIntercept;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * extends WebMvcConfigurerAdapter 已经废弃，java 8 实现 WebMvcConfigurer 接口。
 * --------------------------------------
 * @ClassName: RequestLimitConfig.java
 * @Date: 2021/4/14 10:57
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Slf4j
@Configuration
public class RequestLimitConfig implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(RequestLimitConfig.class);

    @Autowired
    private RequestLimitIntercept requestLimitIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLimitIntercept);
        log.info("添加请求限流拦截器完成....");
    }
}