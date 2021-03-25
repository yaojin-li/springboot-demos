package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.dao.SalariesMapper;
import com.example.demo.base.entity.Salaries;
import com.example.demo.service.SalariesService;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: SalariesController.java
 * @Date: 2021/03/10 23:03:32
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@RestController
@RequestMapping("/salaries")
public class SalariesController {

    @Autowired
    private SalariesService salariesService;

    /**
     * 测试mybatis plus基础功能。
     * 尽量以封装方法调用为主。
     * localhost:8095/salaries/plusTest
     */
    @MethodLog
    @RequestMapping("/plusTest")
    public void mybatisPlusTest() {
        System.out.println("查询总记录数：" + salariesService.count());
    }

    /**
     * 分页查询。
     * localhost:8095/salaries/query
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @MethodLog
    public List<Salaries> query() {
        return salariesService.queryTest(2, 3);
    }

    /**
     * mybatis 分页查询 + 条件查询
     * localhost:8095/salaries/list
     */
    @MethodLog
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(@RequestBody JSONObject params) {
        int current = Integer.valueOf(params.getString("current"));
        int limit = Integer.valueOf(params.getString("limit"));
        Page<Salaries> page = new Page<>(current, limit);
        Map<String, Object> condition = JSON.toJavaObject(params, Map.class);
        List<Map<String, Object>> result = salariesService.selectInfo(page, condition);
        return result;
    }

    /**
     * 使用注解 @Transactional
     * 只在外部调用时生效。在当前类中调用这个方法，依旧会报错。
     * localhost:8095/cursorTransactionalTest/5
     */
    @RequestMapping("/cursorTransactionalTest/{limit}")
    @Transactional
    @MethodLog
    public List<Salaries> cursorTransactionalTest(@PathVariable("limit") int limit) throws Exception {
        List<Salaries> result = new ArrayList<>();
        try (Cursor<Salaries> cursor = salariesService.cursorTest(limit)) {
            cursor.forEach(salaries -> {
                result.add(salaries);
            });
        }
        return result;
    }

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    /**
     * 用 SqlSessionFactory 手动打开数据库连接
     * localhost:8095/cursor/3
     */
    @RequestMapping("/cursor/{limit}")
    @MethodLog
    public List<Salaries> cursorTest(@PathVariable("limit") int limit) throws Exception {
        List<Salaries> result = new ArrayList<>();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession();
                Cursor<Salaries> cursor = sqlSession.getMapper(SalariesMapper.class).cursorTest(limit);
        ) {
            cursor.forEach(salaries -> {
                result.add(salaries);
            });
        }
        return result;
    }


    /**
     * 测试mybatis一级缓存
     * 在同一个sqlsession中，默认为一级缓存
     * 增删改 会强制刷新一级缓存
     * 同一个 SqlSession中, Mybatis会把执行的方法和参数生成缓存的键值，存放在一个 Map 中，后续的键值一样则直接从 Map 中获取数据
     */
    @RequestMapping("/cache")
    @MethodLog
    public void cacheTest() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SalariesMapper mapper = sqlSession.getMapper(SalariesMapper.class);

        Salaries salaries = mapper.cacheTest(10005, "90392");
        System.out.println(salaries.toString());

        System.out.println("=============开始同一个 Sqlsession 的第二次查询============");

        Salaries salaries1 = mapper.cacheTest(10005, "90392");
        System.out.println(salaries1.toString());

        System.out.println(salaries == salaries1);

        System.out.println("=============更新操作，强制刷新一级缓存============");
        Salaries updateSalarie = new Salaries();
        updateSalarie.setEmpNo(10006);
        updateSalarie.setSalary(47518);
        mapper.updateById(updateSalarie);

        System.out.println("=============刷新一级缓存后，同一个 Sqlsession 的第三次查询============");
        Salaries salaries2 = mapper.cacheTest(10005, "90392");
        System.out.println(salaries2.toString());

        System.out.println(salaries == salaries2);

        sqlSession.close();
    }


}
