package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.dao.SalariesMapper;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.MybatisPlusService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.log4j.Log4j2;

/**
 * @Description: --------------------------------------
 * @ClassName: MybatisPlusController.java
 * @Date: 2020/11/22 10:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@RestController
public class MybatisPlusController {

    @Autowired
    public MybatisPlusService mybatisPlusService;

    @MethodLog
    @RequestMapping("/mybatisPlusTest")
    public int mybatisPlusTest(){
        return mybatisPlusService.selectAllCount();
    }

    /**
     * http://localhost:8095/queryTest
     * */
    @RequestMapping("/queryTest")
    @MethodLog
    public Salaries queryTest() {
        return mybatisPlusService.queryTest(2,3);
    }






}
