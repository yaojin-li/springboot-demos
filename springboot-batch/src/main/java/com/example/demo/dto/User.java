package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description: --------------------------------------
 * @ClassName: User.java
 * @Date: 2021/1/12 19:58
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@NoArgsConstructor
@Setter
@Getter
public class User  {
    private Integer id;
    private String username;
    private String sex;
}