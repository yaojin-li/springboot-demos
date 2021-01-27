package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @Description: --------------------------------------
 * @ClassName: User.java
 * @Date: 2021/1/12 19:58
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@AllArgsConstructor
@Setter
@Getter
public class User  {
    private Integer id;
    private String username;
    private String password;

    /**
     * 用户对应角色集合
     * */
    private Set<Role> roles;
}