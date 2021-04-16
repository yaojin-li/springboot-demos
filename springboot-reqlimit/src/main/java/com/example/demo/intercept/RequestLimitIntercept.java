package com.example.demo.intercept;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.RequestLimit;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 请求限制的拦截器
 * --------------------------------------
 * @ClassName: RequestLimitIntercept.java
 * @Date: 2021/4/13 16:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Slf4j
@Component
public class RequestLimitIntercept extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * isAssignableFrom() 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
         * isAssignableFrom()方法是判断是否为某个类的父类
         * instanceof关键字是判断是否某个类的子类
         */
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            //HandlerMethod 封装方法定义相关的信息,如类,方法,参数等
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 获取方法中是否包含注解
            RequestLimit methodAnnotation = method.getAnnotation(RequestLimit.class);
            //获取 类中是否包含注解，也就是controller 是否有注解
            RequestLimit classAnnotation = method.getDeclaringClass().getAnnotation(RequestLimit.class);
            // 如果 方法上有注解就优先选择方法上的参数，否则类上的参数
            RequestLimit requestLimit = methodAnnotation != null ? methodAnnotation : classAnnotation;
            if (requestLimit != null) {
                if (isLimit(request, requestLimit)) {
                    log.error(String.format("接口调用请求限流，请求方法为[%s]...", request.getServletPath()));
                    // TODO 返回自定义结果，根据项目需求调整。
                    responseOut(response, "接口请求频繁，请稍后重试...");
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 判断请求是否受限
     */
    public boolean isLimit(HttpServletRequest request, RequestLimit requestLimit) {
        // 受限的redis 缓存key。sessionid 来做唯一key,如果是app ,可以使用 用户ID 之类的唯一标识。
        String limitKey = request.getServletPath() + request.getSession().getId();
        // 从缓存中获取，当前这个请求访问了几次
        String redisCount = redisTemplate.opsForValue().get(limitKey);
        if (ObjectUtils.isEmpty(redisCount)) {
            //初始 次数
            redisTemplate.opsForValue().set(limitKey, "1", requestLimit.seconds(), TimeUnit.SECONDS);
        } else {
            if (Integer.valueOf(redisCount) >= requestLimit.maxCount()) {
                return true;
            }
            // 次数自增
            redisTemplate.opsForValue().increment(limitKey, 1);
        }
        return false;
    }

    /**
     * 回写给客户端。加密。
     *
     * @param response
     * @param result
     * @throws IOException
     */
    private void responseOut(HttpServletResponse response, String result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        String json = JSONObject.toJSON(result).toString();
        out = response.getWriter();
        // TODO 返回自定义异常结果。可根据需求调整。
        out.append(json);
    }
}