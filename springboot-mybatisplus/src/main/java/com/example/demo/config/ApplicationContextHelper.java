package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: --------------------------------------
 * @ClassName: ApplicationContextHelper.java
 * @Date: 2022/2/15 0015  14:52
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public ApplicationContextHelper() {
    }

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    /**
     * 获得spring上下文
     * @return
     */
    public  ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public  Object getBean(String beanName) {
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }
}
