package demo.filter;//package com.example.demo.logTraceId.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @Description: Zuul网关添加过滤器
// * 生成traceId并通过header传递给下游服务
// --------------------------------------
// * @ClassName: TraceIdGatewayFilter.java
// * @Date: 2022/5/3 0003  18:50
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Component
//public class TraceIdGatewayFilter implements ZuulFilter {
//    @Autowired
//    private TraceProperties traceProperties;
//
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        //根据配置控制是否开启过滤器
//        return traceProperties.getEnable();
//    }
//
//    @Override
//    public Object run() {
//        //链路追踪id
//        String traceId = IdUtil.fastSimpleUUID();
//        MDC.put(CommonConstant.LOG_TRACE_ID, traceId);
//        RequestContext ctx = RequestContext.getCurrentContext();
//        ctx.addZuulRequestHeader(CommonConstant.TRACE_ID_HEADER, traceId);
//        return null;
//    }
//}