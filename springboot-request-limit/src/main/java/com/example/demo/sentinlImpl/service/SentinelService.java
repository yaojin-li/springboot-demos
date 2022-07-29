package com.example.demo.sentinlImpl.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 限流
 * --------------------------------------
 * @ClassName: SentinelService.java
 * @Date: 2021/4/17 10:16
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
@Slf4j
public class SentinelService {
    private static final Logger log = LoggerFactory.getLogger(SentinelService.class);

    /**
     * 使用Sentinel注解实现限流
     * 需要Sentinel切面配置
     */
    @SentinelResource(value = "annotation-test", blockHandler = "handleFlowQpsException", fallback = "flowQpsFallBack")
    public String test(String name) {
        // 需要限流保护的业务逻辑
        // 业务接口...
        if ("exception".equals(name)) {
            // 抛出运行时异常
            throw new RuntimeException();
        }
        return name;
    }

    /**
     * 抛出限流或降级时的处理逻辑
     * 注意: 方法参数、返回值要与原函数保持一致
     */
    public String handleFlowQpsException(String name, BlockException e) {
        log.error("Sentinel注解方式实现接口限流...");
        return ">>>>>>>>>handleFlowQpsException for:" + name;
    }

    /**
     * 接口运行时抛出的异常提供fallback处理
     * 注意: 方法参数、返回值要与原函数保持一致
     */
    public String flowQpsFallBack(String name) {
        log.error("Sentinel注解方式捕获运行时异常...");
        return ">>>>>FallBack for:" + name;
    }

}
