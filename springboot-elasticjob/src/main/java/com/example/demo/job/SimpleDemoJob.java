package com.example.demo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 定义Elastic-Job任务
 * --------------------------------------
 * @ClassName: SimpleDemoJob.java
 * @Date: 2022/1/22 0022  15:09
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class SimpleDemoJob implements SimpleJob {
    Logger logger = LoggerFactory.getLogger(SimpleDemoJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info(String.format("Thread id[%s]，作业分片总数[%s]，当前分片项[%s]，当前参数[%s]，" +
                        "作业名称[%s]，作业自定义参数[%s]",
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()));
    }
}
