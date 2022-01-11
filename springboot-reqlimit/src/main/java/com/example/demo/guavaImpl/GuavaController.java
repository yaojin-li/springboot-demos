package com.example.demo.guavaImpl;

import com.example.demo.redisImpl.intercept.RequestLimitIntercept;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 通过Guava的RateLimiter限流
 * 适用于：调用特定接口限流（定时任务等）
 * --------------------------------------
 * @ClassName: GuavaController.java
 * @Date: 2022/1/11 0011  15:55
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class GuavaController {
    private static final Logger log = LoggerFactory.getLogger(GuavaController.class);

    /**
     * 通过guava构建限流器
     * QPS: 1笔请求/s
     * */
    RateLimiter rateLimiter = RateLimiter.create(1);

    @RequestMapping("guava")
    public void test(){
        while (true){
            rateLimiter.acquire();
            log.info("限流中...");
        }
    }


}
