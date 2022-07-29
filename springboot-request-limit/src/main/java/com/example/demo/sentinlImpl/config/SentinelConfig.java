package com.example.demo.sentinlImpl.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     * sentinel 切面配置类
     * */
    @Bean
    SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }

    /**
     * 定义限流规则
     * 此处由springboot自动扫描生成bean对象。
     */
    @Bean
    public static void initFlowRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule flowRule = new FlowRule();
        // TODO 设置限流资源，即对哪些接口限流，对应@SentinelResource()注解的value=annotation-test值
        flowRule.setResource("annotation-test");
        // 流控针对的调用来源，若为 default 则不区分调用来源
        flowRule.setLimitApp("default");
        // 配置类型。FLOW_GRADE_QPS：QPS，FLOW_GRADE_THREAD：线程数
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // TODO QPS的值。若配置线程数类型，count代表线程数
        // 每秒1次，超过则拦截。可配置。
        flowRule.setCount(1);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 定义单个熔断规则
     * 原理：
     * 1. 当资源的平均响应时间（RT，上限 4900 ms）超过阈值（count）后，资源进入准降级状态。
     * 2. 接下来如果持续进入 5 个请求，RT 都持续超过这个阈值，在接下的时间窗口（timeWindow）之内对这个方法的调用都会自动地返回（抛出 DegradeException）。
     * 例：
     * 1秒内的5个请求，平均响应时间大于10ms，接下来的3秒内都会自动熔断。
     */
    @Bean
    public void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        // TODO 设置熔断资源，即对哪些接口熔断，对应@SentinelResource()注解的value=degrade-test值
        rule.setResource("degrade-test");
        // TODO 某个服务下的所有接口设置为熔断资源
//        rule.setResource("com.example.demo.sentinlImpl.service.DegradeService");
        // 阈值 - 1ms
        rule.setCount(1);
        // 熔断策略 - RT模式（资源平均响应时间）
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 时间窗口 - 3s
        rule.setTimeWindow(3);
        // TODO RT模式下1秒内连续多少个请求的平均RT超出阈值，才可以触发熔断。可配置。
        rule.setRtSlowRequestAmount(1);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

}
