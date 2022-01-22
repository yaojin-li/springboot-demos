package com.example.demo.conf;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.example.demo.job.SimpleDemoJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: --------------------------------------
 * @ClassName: SimpleJobConfig.java
 * @Date: 2022/1/22 0022  15:23
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class SimpleJobConfig {

    private final String cron = "0/5 * * * * ?";
    private final int shardingTotalCount = 3;
    private final String shardingItemParameters = "0=A,1=B,2=C";
    private final String jobParameters = "parameter";

    @Autowired
    private ZookeeperRegistryCenter registryCenter;

    @Bean
    public SimpleJob stockJob() {
        return new SimpleDemoJob();
    }

    private LiteJobConfiguration getLiteJobConfiguration(Class<? extends SimpleJob> jobClass,
                                                         String cron,
                                                         int shardingTotalCount,
                                                         String shardingItemParameters,
                                                         String jobParameters) {
        // 定义作业核心配置
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount).
                shardingItemParameters(shardingItemParameters).
                jobParameter(jobParameters).build();
        // 定义simple类型配置
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(simpleCoreConfig, jobClass.getCanonicalName());
        // 定义lite作业根配置
        return LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(SimpleJob simpleJob) {
        return new SpringJobScheduler(
                simpleJob,
                registryCenter,
                getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters, jobParameters)
        );
    }

}
