package com.example.demo.annotation;

import com.example.demo.enums.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 数据源切换注解
 * --------------------------------------
 * @ClassName: DataSourceSwitch.java
 * @Date: 2020/11/18 19:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceSwitch {
    DataSourceEnum value() default DataSourceEnum.MASTER;
}
