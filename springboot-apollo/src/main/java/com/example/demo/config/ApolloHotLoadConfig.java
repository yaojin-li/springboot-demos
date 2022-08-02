//package com.example.demo.config;
//
//import com.ctrip.framework.apollo.core.ConfigConsts;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * @Description: apollo 热加载配置文件
// * --------------------------------------
// * @ClassName: ApolloHotLoadConfig.java
// * @Date: 2022/08/02 18:44
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Configuration
//@EnableConfigurationProperties(DataSourceProperties.class)
//@Slf4j
//public class ApolloHotLoadConfig {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//    @Autowired
//    private org.springframework.cloud.context.scope.refresh.RefreshScope refreshScope;
//
//    @RefreshScope
//    @Bean("dataSource_apollo")
//    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
//        return dataSourceProperties.initializeDataSourceBuilder().build();
//    }
//
//    /**
//     * 监听Apollo配置变更
//     */
//    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION, "dev"},
//            interestedKeyPrefixes = {"spring.datasource"})
//    public void onChange() {
//        // 重新编译 dataSource 初始化bean
//        refreshScope.refresh("dataSource_apollo");
//        log.info("Apollo 配置内容变更：{}", applicationContext.getBean(DataSourceProperties.class).toString());
//    }
//
//
//}
//
//
//
//
//
//
