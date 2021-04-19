package com.example.demo.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: sentinel 配置
 * --------------------------------------
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
     * 定义单个限流规则
     * 此处由springboot自动扫描生成bean对象，默认只对 testLimit 接口限流。
     */
    @Bean
    public static void initFlowRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule flowRule = new FlowRule();
        // TODO 设置限流资源，即对哪个接口限流。此处测试接口，对应value=annotation的@SentinelResource()注解
        flowRule.setResource("annotation");
        // 流控针对的调用来源，若为 default 则不区分调用来源
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
     * 定义单个熔断规则
     * 1秒内的5个请求，平均响应时间大于10ms，接下来的3秒内都会自动熔断。
     */
    @Bean
    public void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        // TODO 设置熔断资源，即对哪个接口熔断。此处测试接口，对应value=degrade的@SentinelResource()注解
//        rule.setResource("com.example.demo.service.DegradeService");
        rule.setResource("degrade");
        // 阈值 - 10ms
        rule.setCount(10);
        // 熔断策略 - RT模式
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 时间窗口 - 3s
        rule.setTimeWindow(3);
        // RT模式下,1秒内连续多少个请求的平均RT超出阈值，才可以触发熔断
        rule.setRtSlowRequestAmount(5);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

}
