package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import com.example.demo.dto.User;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;


/**
 * @Description: shiro 测试
 * 1.先调用login登录；
 * 2.再调用其他方法测试；
 * --------------------------------------
 * @ClassName: ShiroController.java
 * @Date: 2021/1/26 19:21
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@Log4j2
public class ShiroController {

    @GetMapping("/login")
    @MethodLog
    public String login(User user) {
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
            return "输入用户名密码。";
        }

        // 用户认证信息
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(), user.getPassword());

        try {
            // 信息验证
            currentUser.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限！";
        }
        return "login success...";
    }

    @GetMapping("/admin")
    @RequiresRoles("admin")
    public String admin() {
        return "admin success...";
    }

    @GetMapping("/index")
    @RequiresPermissions("query")
    public String index() {
        return "index success...";
    }

    @GetMapping("/add")
    @RequiresPermissions("add")
    public String add() {
        return "add success...";
    }

}
