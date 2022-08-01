package com.example.demo.aop;

import com.example.demo.annotation.DataSourceSwitch;
import com.example.demo.config.DynamicDataSource;
import com.example.demo.enums.DataSourceEnum;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: joinPoint 使用注解 DataSourceSwitch 时，切换数据源
 * --------------------------------------
 * @ClassName: DynamicDataSourceAop.java
 * @Date: 2020/11/18 19:40
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@Aspect
@Configuration
public class DynamicDataSourceAop {

    @Around("@annotation(dataSourceSwitch)")
    public Object around(ProceedingJoinPoint joinPoint, DataSourceSwitch dataSourceSwitch) throws Throwable{

        // 切换数据源
        DynamicDataSource.setDataSource(dataSourceSwitch.value());
        log.info(String.format("数据源切换成[%s]",  dataSourceSwitch.value()));

        Object result;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } finally {
            DynamicDataSource.removeDataSource();
        }
        return result;
    }
}
