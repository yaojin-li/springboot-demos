package com.example.demo.shiro;

import com.example.demo.dto.Permissions;
import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tomcat.util.http.parser.Authorization;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

/**
 * @Description: 自定义Realm用于查询用户的角色和权限信息并保存到权限管理器
 * --------------------------------------
 * @ClassName: CustomRealm.java
 * @Date: 2021/1/27 18:09
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 权限配置类
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户
        User user = loginService.getUserByName(name);
        // 为用户添加角色与权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 遍历用户的role与permission
        for (Role role : user.getRoles()) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            // 添加权限
            for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证配置类
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 校验用户
        if (ObjectUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }

        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.getUserByName(name);

        if (null == user) {
            // 返回异常
            return null;
        } else {
            // 验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(name, user.getPassword(), getName());
        }
    }
}
