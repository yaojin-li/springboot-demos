package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import com.example.demo.base.dao.SalariesMapper;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.MybatisPlusService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: MybatisCursorController.java
 * @Date: 2020/12/15 19:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@RestController
public class MybatisCursorController {

    @Autowired
    public MybatisPlusService mybatisPlusService;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    /**
     * 用 SqlSessionFactory 手动打开数据库连接
     */
    @RequestMapping("/cursor/{limit}")
    @MethodLog
    public List<Salaries> cursorTest(@PathVariable("limit") int limit) throws Exception {
        List<Salaries> result = new ArrayList<>();
        try (
                SqlSession session = sqlSessionFactory.openSession();
                Cursor<Salaries> cursor = session.getMapper(SalariesMapper.class).cursorTest(limit);
        ) {
            cursor.forEach(salaries -> {
                result.add(salaries);
            });
        }
        return result;
    }


    /**
     * 使用注解 @Transactional
     * 只在外部调用时生效。在当前类中调用这个方法，依旧会报错。
     */
    @RequestMapping("/cursorTransactionalTest/{limit}")
    @Transactional
    @MethodLog
    public List<Salaries> cursorTransactionalTest(@PathVariable("limit") int limit) throws Exception {
        List<Salaries> result = new ArrayList<>();
        try (Cursor<Salaries> cursor = mybatisPlusService.cursorTest(limit)) {
            cursor.forEach(salaries -> {
                result.add(salaries);
            });
        }
        return result;
    }

}
