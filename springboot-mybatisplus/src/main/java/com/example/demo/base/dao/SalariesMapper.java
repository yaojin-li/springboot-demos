package com.example.demo.base.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.base.entity.Salaries;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;
import java.util.Map;

/**
 * 使用 mybatisplus
 * mapper中继承BaseMapper，不能指定泛型<T>，需要指明具体的<T>中的vo
 * */
public interface SalariesMapper extends BaseMapper<Salaries> {

    /**
     * 测试mybatis流式查询（Cursor）
     * */
    Cursor<Salaries> cursorTest(@Param("limit") int limit);

    /**
     * 测试mybatis分页查询
     * */
    List<Map<String, Object>> selectInfo(@Param("page") Page<Salaries> page,
                                         @Param("condition") Map<String, Object> condition);

    /**
     * 测试缓存
     * */
    Salaries cacheTest(@Param("empNo") int empNo, @Param("salary") String salary);

}