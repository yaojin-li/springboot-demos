package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.dao.SalariesMapper;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.MybatisPlusService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.HsqlSequenceMaxValueIncrementer;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

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
    public int mybatisPlusTest() {
        return mybatisPlusService.selectAllCount();
    }

    /**
     * http://localhost:8095/queryTest
     */
    @RequestMapping("/queryTest")
    @MethodLog
    public List<Salaries> queryTest() {
        return mybatisPlusService.queryTest(2, 3);
    }


}
