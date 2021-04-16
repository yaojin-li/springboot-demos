package com.example.demo.config;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: SentinelConfig.java
 * @Date: 2021/4/16 15:24
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Slf4j
@Configuration
public class SentinelConfig {
    /**
     * 加载限流规则
     */
    @Bean
    public static void initFlowRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule flowRule = new FlowRule();
        // 设置资源
        flowRule.setResource("testLimit");
        flowRule.setLimitApp("default");
        // 配置类型 QPS
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // QPS的值。若配置线程数类型，count代表线程数
        // 每秒3次，超过则拦截
        flowRule.setCount(3);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 执行方法
     */
    public static boolean isLimited() {
        Entry entry = null;
        try {
            entry = SphU.entry("testLimit");
            log.info("limited...");
            return true;
        } catch (Exception e) {
            if (FlowException.isBlockException(e)) {
                // 请求拦截
                log.error("blocked...");
            }
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return false;
    }
}
