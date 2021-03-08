package com.generator.code.entity;

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
 * @Description: Titles实体类
 * --------------------------------------
 * @ClassName: Titles.java
 * @Date: 2021/03/08 12:12:41
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */

@Data
@TableName("titles" )
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Titles对象" , description = "Titles对象" )
public class Titles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "emp_no" , type = IdType.AUTO)
    private Integer empNo;

    @TableField("title" )
    private String title;

    @TableField("from_date" )
    private LocalDate fromDate;

    @TableField("to_date" )
    private LocalDate toDate;


    public static final String EMP_NO = "emp_no";

    public static final String TITLE = "title";

    public static final String FROM_DATE = "from_date";

    public static final String TO_DATE = "to_date";

}