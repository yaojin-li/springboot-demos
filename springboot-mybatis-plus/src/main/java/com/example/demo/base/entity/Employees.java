package com.example.demo.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Description: Employees实体类
 * --------------------------------------
 * @ClassName: Employees.java
 * @Date: 2021/03/26 11:48:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */

@Data
@TableName("employees")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Employees对象", description = "Employees对象")
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "emp_no", type = IdType.AUTO)
    private Integer empNo;

    @TableField("birth_date")
    private LocalDate birthDate;

    @TableField("first_name")
    private String firstName;

    @TableField("last_name")
    private String lastName;

    @TableField("gender")
    private String gender;

    @TableField("hire_date")
    private LocalDate hireDate;


    public static final String EMP_NO = "emp_no";

    public static final String BIRTH_DATE = "birth_date";

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "last_name";

    public static final String GENDER = "gender";

    public static final String HIRE_DATE = "hire_date";

}