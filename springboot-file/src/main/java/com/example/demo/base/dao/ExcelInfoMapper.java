package com.example.demo.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.base.vo.ExcelInfo;

public interface ExcelInfoMapper extends BaseMapper<ExcelInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(ExcelInfo record);

    int insertSelective(ExcelInfo record);

    ExcelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcelInfo record);

    int updateByPrimaryKey(ExcelInfo record);
}