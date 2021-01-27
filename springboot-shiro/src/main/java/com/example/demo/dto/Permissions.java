package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @Description: 权限对应实体类
 * --------------------------------------
 * @ClassName: Permissions.java
 * @Date: 2021/1/12 19:58
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@AllArgsConstructor
@Setter
@Getter
public class Permissions {
    private Integer id;
    private String permissionsName;
}
