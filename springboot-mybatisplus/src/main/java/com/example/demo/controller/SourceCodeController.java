package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import com.example.demo.base.dao.EmployeesMapper;
import com.example.demo.base.entity.Employees;
import com.mysql.cj.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: mybatis 源码分析
 * --------------------------------------
 * @ClassName: SourceCodeController.java
 * @Date: 2021/3/28 11:38
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@Slf4j
public class SourceCodeController {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @RequestMapping("/sourceCode")
    @MethodLog
    public void sourceCode() {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口的代理对象
        EmployeesMapper mapper = sqlSession.getMapper(EmployeesMapper.class);
        // 调用代理对象方法
        Employees employees = mapper.selectById(10005);
        log.info(employees.toString());
        // 关闭session
        sqlSession.close();
    }

}
