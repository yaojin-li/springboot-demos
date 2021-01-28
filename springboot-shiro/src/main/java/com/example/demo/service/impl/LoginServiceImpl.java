package com.example.demo.service.impl;

import com.example.demo.dto.Permissions;
import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 模拟数据库，构建vo对象，分配权限。
 * --------------------------------------
 * @ClassName: LoginService.java
 * @Date: 2021/1/27 18:11
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public User getUserByName(String getMapByName) {
        Map<String, User> result = new HashMap<>();

        // 设置权限
        Permissions queryPermission = new Permissions(1, "query");
        Permissions addPermission = new Permissions(2, "add");

        // 配置查询角色
        Set<Permissions> querySet = new HashSet<>();
        querySet.add(queryPermission);
        // 新建普通user角色
        Role queryRole = new Role(1, "user", querySet);
        // 配置查询用户
        Set<Role> roles = new HashSet<>();
        roles.add(queryRole);
        // 新建普通用户
        User user = new User(1, "zhangsan", "abcdef", roles);
        result.put(user.getName(), user);

        // 配置查询、新增角色
        Set<Permissions> addSet = new HashSet<>();
        addSet.add(queryPermission);
        addSet.add(addPermission);
        // 新建admin角色
        Role addRole = new Role(2, "admin", addSet);
        // 配置新增用户
        Set<Role> roles1 = new HashSet<>();
        roles1.add(addRole);
        // 新建admin用户
        User admin = new User(2, "admins", "123456", roles1);
        result.put(admin.getName(), admin);

        return result.get(getMapByName);
    }
}
