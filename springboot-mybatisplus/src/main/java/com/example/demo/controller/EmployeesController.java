package com.example.demo.controller;


import com.example.demo.annotation.MethodLog;
import com.example.demo.base.dao.EmployeesMapper;
import com.example.demo.base.entity.Employees;
import com.example.demo.service.EmployeesService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: EmployeesController.java
 * @Date: 2021/03/26 11:48:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    public EmployeesService employeesService;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    /**
     * 测试mybatis自定义缓存
     */
    @RequestMapping("/cache")
    @MethodLog
    public void cache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeesMapper mapper = sqlSession.getMapper(EmployeesMapper.class);

        System.out.println("=======通过封装方法测试=======");
        Employees employees = mapper.selectById(10003);
        System.out.println(employees);
        sqlSession.close();

        System.out.println("=======通过自定义方法测试=======");
        Employees employees1 = employeesService.cacheTest(10003);
        System.out.println(employees1);

        System.out.println(employees == employees1);
    }

}



