package com.example.demo.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: --------------------------------------
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

    private static final String RESOURCE_NAME = "degrade";

    /**
     * 1.抛出异常的方式定义资源
     * 代码侵入性高
     */
    public void limit() throws InterruptedException {
        int count = 0;
        boolean stop = false;
        while (!stop) {
            Entry entry = null;
            try {
                entry = SphU.entry(RESOURCE_NAME);
                log.info("业务操作...{}", count);
                Thread.sleep(15);
            } catch (BlockException e) {
                if (e instanceof DegradeException) {
                    log.error("触发熔断机制...{}", count);
                    Thread.sleep(500);
                }
            } finally {
                if (entry != null) {
                    entry.exit();
                }
                if (count >= 20) {
                    stop = true;
                }
            }
            count++;
        }
        log.info("----------------------------");
    }

    /**
     * 2. 使用Sentinel注解实现限流
     */
    @SentinelResource(value = "degrade", blockHandler = "degradeException", fallback = "degradeFallBack")
    public String degrade(String name) {
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
        // TODO 判断异常为DegradeException时触发熔断降级
        if (e instanceof DegradeException) {
            log.error("degradeException 接口熔断降级...");
        }
        return ">>>>>>>>>degradeException for:" + name;
    }

    /**
     * 接口运行时抛出的异常提供fallback处理
     * 注意: 方法参数、返回值要与原函数保持一致
     */
    public String degradeFallBack(String name, Throwable e) {
        log.error("degradeFallBack 捕获运行时异常...");
        return ">>>>>FallBack for:" + name;
    }


}
