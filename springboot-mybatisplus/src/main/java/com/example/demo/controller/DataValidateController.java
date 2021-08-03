package com.example.demo.controller;

import com.example.demo.base.entity.DataValidate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description: --------------------------------------
 * @ClassName: DataValidateController.java
 * @Date: 2021/8/3 11:25
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@RequestMapping("/dataValidate")
public class DataValidateController {

    /**
     * 测试 Hibernate-validate 框架中的注解
     * 在校验的实体 DataSetSaveVO 旁边添加@Valid或@Validated注解
     * url：localhost:8095/dataValidate/test
     * headers：application/json
     * body：
     * {
     * "userUuid": "#",
     * "name": "张三",
     * "description": ""
     * }
     */
    @PostMapping(value = "/test")
    public DataValidate test(@Valid @RequestBody DataValidate dataValidate) {
        System.out.println(dataValidate);
        return dataValidate;
    }

}
