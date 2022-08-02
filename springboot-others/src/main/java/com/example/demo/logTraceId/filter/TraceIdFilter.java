package com.example.demo.logTraceId.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description: 通过过滤器向本服务日志中添加traceId
 * --------------------------------------
 * @ClassName: TraceIdFilter.java
 * @Date: 2022/5/3 0003  18:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Order(0)
@WebFilter(filterName = "traceIdFilter", urlPatterns = "/*")
@Component
public class TraceIdFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdFilter.class);
    public static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID, traceId);
        LOGGER.info("TraceIdFilter create traceId:{}", traceId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        MDC.remove(TRACE_ID);
    }

}
