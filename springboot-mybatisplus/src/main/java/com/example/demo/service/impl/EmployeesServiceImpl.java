package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.base.entity.Employees;
import com.example.demo.base.dao.EmployeesMapper;
import com.example.demo.service.EmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: EmployeesServiceImpl.java
 * @Date: 2021/03/26 11:48:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements EmployeesService {

    @Autowired
    public EmployeesMapper employeesMapper;

    @Override
    public Employees cacheTest(int id) {
        return employeesMapper.selectById(id);
    }

    @DS("master")
    public Employees MasterDbTest(int id){
        return employeesMapper.selectById(id);
    }

}
