package com.example.demo.base.dao;

import com.example.demo.base.vo.ExcelInfo;

public interface ExcelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExcelInfo record);

    int insertSelective(ExcelInfo record);

    ExcelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcelInfo record);

    int updateByPrimaryKey(ExcelInfo record);
}