package com.example.demo.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: --------------------------------------
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

    private static final String RESOURCE_NAME = "sentinel";

    /**
     * 1.抛出异常的方式定义资源
     * 代码侵入性高
     */
    public String limit() {
        Entry entry = null;
        try {
            // 入口登记
            entry = SphU.entry(RESOURCE_NAME);

            // 需要限流保护的业务逻辑
            // 业务接口...

        } catch (Exception e) {
            if (FlowException.isBlockException(e)) {
                // 请求拦截
                log.error("testLimit接口限流...");
                return "接口限流...";
            }
            log.error("接口异常", e);
        } finally {
            // SphU.entry(xxx) 需要与 entry.exit() 成对出现,否则会导致调用链记录异常
            if (entry != null) {
                entry.exit();
            }
        }
        return "testLimit 完成...";
    }


    /**
     * 2. 使用Sentinel注解实现限流
     * 需要Sentinel切面配置
     */
    @SentinelResource(value = "annotation", blockHandler = "handleFlowQpsException", fallback = "flowQpsFallBack")
    public String annotation(String name) {
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
        log.error("annotation注解方式实现接口限流...");
        return ">>>>>>>>>handleFlowQpsException for:" + name;
    }

    /**
     * 接口运行时抛出的异常提供fallback处理
     * 注意: 方法参数、返回值要与原函数保持一致
     */
    public String flowQpsFallBack(String name) {
        log.error("annotation注解方式捕获运行时异常...");
        return ">>>>>FallBack for:" + name;
    }

}
