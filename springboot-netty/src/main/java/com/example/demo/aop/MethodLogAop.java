package com.example.demo.aop;

import com.alibaba.fastjson.JSON;
import com.example.demo.annotation.MethodLog;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @Description: joinPoint 获取切面参数，记录日志
 * --------------------------------------
 * @ClassName: MethodLogAop.java
 * @Date: 2020/11/12 18:10
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@Aspect
@Configuration
public class MethodLogAop {

    @Around("@annotation(methodLog)")
    public Object around(ProceedingJoinPoint joinPoint, MethodLog methodLog) throws Throwable {

        // 获取切面方法参数
        Object[] args = joinPoint.getArgs();
        // 获取切面方法的class对象
        Class clazz = joinPoint.getTarget().getClass();
        // 从切面上获取目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = clazz.getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        // 获取目标名称
        String targetName = "[" + clazz.getName() + "." + method.getName() + "]";
        log.info(String.format("进入方法：%s - args: %s", targetName, JSON.toJSONString(args)));

        long beginTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed(args);
            return result;
        } finally {
            long endTime = System.currentTimeMillis();
            long usedTime = endTime - beginTime;
            log.info(String.format("离开方法：%s - return: %s", targetName, JSON.toJSONString(result)));
            log.info(String.format("%s方法耗时：%sms", targetName, usedTime));
        }
    }
}
