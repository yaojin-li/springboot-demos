package com.example.demo.taskRetry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description: 异常重试
 * 多个节点部署应用时，先竞争资源获取Redis分布式锁，再操作数据。异常重试。
 * --------------------------------------
 * @ClassName: RequestRetry.java
 * @Date: 2021/12/1 19:29
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class RequestRetry {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestRetry.class);

    // 定义重试次数
    private static final Integer RETRY_NUM = 3;

    @Scheduled()
    public void delRequestRecordScheduled() {
        // 先判断该节点是否获取到Redis分布式锁

        //
        LOGGER.info("获得并发锁，开始【xxx】业务处理...");

        // 定义异常重试次数
        int time = 1;
        while (true) {
            if (time > RETRY_NUM) {
                LOGGER.error(String.format("【xxx】重试次数超过最大次数%d次，放弃任务", RETRY_NUM));
                break;
            }

            try {
                // 正常的业务处理...
                break;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(String.format("【xxx】业务处理异常，第%d次失败", time));
                time++;
            }
        }
        LOGGER.info("【xxx】业务处理结束");
    }

}
