package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @Description: --------------------------------------
 * @ClassName: Role.java
 * @Date: 2021/1/12 19:58
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@AllArgsConstructor
@Setter
@Getter
public class Role {
    private Integer id;
    private String roleName;

    /**
     * 角色对应权限集合
     * */
    private Set<Permissions> permissions;
}