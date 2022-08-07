package com.example.demo.config;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description: apollo 热加载配置，监控文件
 * 1. 此监控文件监听Apollo上配置：新增、删除、修改 等内容；
 * 2. 高版本Apollo依赖本身提供的热部署监控：无法监控新增操作（只监听程序中使用的配置）、无法显示修改前的内容；
 * 3. 高版本Apollo依赖本身提供的热部署监控：可以监控所有namespace的改动，此监控文件需要在 @ApolloConfigChangeListener中指定监控的namespace，默认为application。
 * --------------------------------------
 * @ClassName: ApolloHotLoadConfig.java
 * @Date: 2022/08/02 18:44
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
@Slf4j
public class ApolloHotLoadConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RefreshScope refreshScope;

    /**
     * 监听注解。可指定多个namespace
     * 指定Apollo的namespace，非常重要，如果不指定，默认只使用application！！！
     */
    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION, "namespace-test"})
    private void changeHandlder(ConfigChangeEvent configChangeEvent) {
        refreshProperties(configChangeEvent);
    }

    /**
     * 监听配置并热部署
     */
    private void refreshProperties(ConfigChangeEvent configChangeEvent) {
        Set<String> keySet = configChangeEvent.changedKeys();
        for (String key : keySet) {
            log.info("[配置修改]key为：" + key + "，修改内容：" + configChangeEvent.getChange(key));
        }
        // 原有的配置已经注入到对应属性中，需要解绑并重新绑定
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(configChangeEvent.changedKeys()));
        // 刷新重新绑定后的配置
        refreshScope.refreshAll();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}






