package com.example.demo.base.dao;

import com.example.demo.base.entity.Employees;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: EmployeesMapper.java
 * @Date: 2021/03/26 11:48:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface EmployeesMapper extends BaseMapper<Employees> {

    /**
     * 测试自定义缓存
     * */
    Employees selectById(int id);

}
