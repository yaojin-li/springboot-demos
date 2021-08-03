package com.example.demo.base.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @Description: 测试 Hibernate-validate 框架中的注解
 * --------------------------------------
 * @ClassName: DataValidate.java
 * @Date: 2021/8/2 21:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Data
public class DataValidate {

    @NotBlank(message = "uuid为空")
    @Pattern(regexp = "^[a-z0-9]+$", message = "userUuid 名称只能是字母和数字")
    @Length(max = 48, message = "长度超过48字符")
    private String userUuid;

    @NotBlank(message = "姓名为空")
    private String name;

    @Length(max = 256, message = "数据描述超过256字符")
    @NotBlank(message = "数据描述为空")
    private String description;

}




















