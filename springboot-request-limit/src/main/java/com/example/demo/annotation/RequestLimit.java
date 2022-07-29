package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 请求限制的自定义注解
 * --------------------------------------
 * @ClassName: RequestLimit.java
 * @Date: 2021/4/15 22:23
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestLimit {
    // 时间范围
    int seconds();

    // 请求最大次数
    int maxCount();
}
