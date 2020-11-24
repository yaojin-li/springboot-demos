package com.example.demo.base.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.base.vo.Salaries;

/**
 * 使用 mybatisplus
 * mapper中继承BaseMapper，不能指定泛型<T>，需要指明具体的<T>中的vo
 * */
public interface SalariesMapper extends BaseMapper<Salaries> {

    int selectAllCount();

}