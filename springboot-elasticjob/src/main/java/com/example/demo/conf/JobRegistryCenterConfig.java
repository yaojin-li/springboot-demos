package com.example.demo.conf;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: --------------------------------------
 * @ClassName: JobRegistryCenterConfig.java
 * @Date: 2022/1/22 0022  15:03
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class JobRegistryCenterConfig {

    @Value("${elasticjob.regCenter.namespace}")
    String namespace;
    @Value("${elasticjob.regCenter.serverLists}")
    String serverList;

    @Value("${elasticjob.sessionTimeout}")
    Integer sessionTimeout;
    @Value("${elasticjob.connectionTimeout}")
    Integer connectionTimeout;
    @Value("${elasticjob.maxSleepTime}")
    Integer maxSleepTime;
    @Value("${elasticjob.baseSleepTime}")
    Integer baseSleepTime;

    /**
     * 配置zookeeper
     * */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter() {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverList, namespace);
        zookeeperConfiguration.setSessionTimeoutMilliseconds(sessionTimeout);
        zookeeperConfiguration.setConnectionTimeoutMilliseconds(connectionTimeout);
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(maxSleepTime);
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(baseSleepTime);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }
}
