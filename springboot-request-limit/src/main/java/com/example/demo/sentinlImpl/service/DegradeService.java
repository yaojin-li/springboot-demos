package com.example.demo.sentinlImpl.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 熔断降级
 * --------------------------------------
 * @ClassName: DegradeService.java
 * @Date: 2021/4/17 17:40
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
@Slf4j
public class DegradeService {
    private static final Logger log = LoggerFactory.getLogger(DegradeService.class);

    /**
     * 使用Sentinel注解实现熔断降级
     */
    @SentinelResource(value = "degrade-test", blockHandler = "degradeException", fallback = "degradeFallBack")
    public String degrade(String name) {
        // 业务处理
        // ......

        if ("exception".equals(name)) {
            // 抛出运行时异常
            throw new RuntimeException();
        }
        // 业务处理
        // ......
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 抛出限流或降级时的处理逻辑
     * 注意: 方法参数、返回值要与原函数保持一致
     */
    public String degradeException(String name, BlockException e) {
        // 判断异常为DegradeException时触发熔断降级
        if (e instanceof DegradeException) {
            log.error("Sentinel degrade Exception 接口熔断降级...");
        }
        return ">>>>>>>>>Degrade Exception for:" + name;
    }

    /**
     * 接口运行时抛出的异常提供fallback处理
     * 注意: 方法参数、返回值要与原函数保持一致
     */
    public String degradeFallBack(String name, Throwable e) {
        log.error("Degrade FallBack 捕获运行时异常...");
        return ">>>>>Fall Back for:" + name;
    }


}
