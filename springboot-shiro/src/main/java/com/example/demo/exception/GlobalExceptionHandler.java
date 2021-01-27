package com.example.demo.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Description: 异常拦截处理
 * 注解验证角色和权限无法捕捉异常，从而无法正确的返回给前端错误信息
 * 具体异常拦截处理
 * --------------------------------------
 * @ClassName: GlobalExceptionHandler.java
 * @Date: 2021/1/27 23:27
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public String errorHandler(AuthorizationException e) {
        log.error("没有通过权限验证", e);
        return "没有通过权限验证。。";
    }
}
