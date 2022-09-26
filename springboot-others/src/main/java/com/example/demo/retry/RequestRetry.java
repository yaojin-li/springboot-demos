package com.example.demo.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 异常重试
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
    private static final Integer MAX_RETRY_COUNT = 3;
    @Autowired
    RetryService service;

    public void retry() throws Exception {
        // 定义异常重试次数
        int retryCount = 1;
        while (retryCount <= MAX_RETRY_COUNT) {
            try {
                // 模拟数据库操作
//                service.list()
                break;
            } catch (Exception e) {
                LOGGER.error(String.format("从数据库第[%s]次[查询数据]异常，异常信息：", retryCount) + e);
                TimeUnit.SECONDS.sleep(5);
            }
            retryCount++;
        }
        LOGGER.info("[xxx]业务处理结束");
    }

}
