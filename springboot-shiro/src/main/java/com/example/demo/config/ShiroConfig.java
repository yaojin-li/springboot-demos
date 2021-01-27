package com.example.demo.config;

import com.example.demo.shiro.CustomRealm;
import com.ulisesbocchio.jasyptspringboot.annotation.ConditionalOnMissingBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: shiro 配置文件
 * 把CustomRealm和SecurityManager等注入到spring容器
 * --------------------------------------
 * @ClassName: ShiroConfig.java
 * @Date: 2021/1/27 18:06
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class ShiroConfig {

    /**
     * 添加自定义的权限、角色的验证方式
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    /**
     * 基于spring自动代理方式为service创建代理对象,实现权限控制
     * */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


}








