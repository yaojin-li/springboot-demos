package com.example.demo.logTraceId.intrceptor;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description: 通过拦截器向本服务日志中打印接收到的上游服务的traceId，或
 * 新增traceId加入到header中传到下游服务
 * --------------------------------------
 * @ClassName: TraceIdInterceptor.java
 * @Date: 2022/5/3 0003  17:42
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TraceIdInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdInterceptor.class);
    private static final String TRACE_ID = "traceId";

    /**
     * 接收上游服务的traceId到本服务中 或
     * 新增traceId加入到header中传到下游服务
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceIdInHeader = request.getHeader(TRACE_ID);
        if (StringUtils.isNotBlank(traceIdInHeader)) {
            LOGGER.info("上游服务header中traceId为：{}...", traceIdInHeader);
            MDC.put(TRACE_ID, traceIdInHeader);
        } else {
            String traceId = createTraceId();
            response.setHeader(TRACE_ID, traceId);
            LOGGER.info("上游服务header中无traceId，新增traceId：{} 并传入下游服务...", traceId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        destroyTraceId();
    }

    public static String createTraceId() {
        String traceId = MDC.get(TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
            LOGGER.info("TraceIdInterceptor create traceId:{}", traceId);
            MDC.put(TRACE_ID, traceId);
        }
        return traceId;
    }

    public static void destroyTraceId() {
        MDC.remove(TRACE_ID);
    }

}
