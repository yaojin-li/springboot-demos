package com.example.demo.config;

import com.example.demo.shiro.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import com.ulisesbocchio.jasyptspringboot.annotation.ConditionalOnMissingBean;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
     * 基于spring自动代理方式为service创建代理对象,实现权限控制
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 添加自定义的权限、角色的验证方式
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }


    /**
     * 权限管理，配置主要是Realm的管理认证
     * SecurityManager: 安全管理器，管理所有Subject
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }


    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 错误页面，认证不通过则跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");

        /**
         * 配置shiro拦截器链
         * anon     不需要认证
         * authc    需要认证
         * user     验证通过或rememberMe登录的都可以
         *
         * 当应用开启了rememberMe时，用户下次访问时可以是一个user，但不会是authc，因为authc是需要重新认证的
         *
         * 顺序从上到下，优先级依次降低
         *
         * */
        Map<String, String> map = new HashMap<>(10);
        // 对所有app路径下的请求不拦截
        map.put("/app/***", "anon");
        // 对swagger接口页面的请求不拦截
        map.put("/swagger-ui.html", "anon");
        // 登出
        map.put("/logout", "logout");
        // 对所有用户认证
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }


    /**
     * 注解访问授权动态拦截
     * 匹配所有类，匹配所有加了认证注解的方法。即匹配 @RequiresPermissions()、@RequiresRoles() 等
     * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}

