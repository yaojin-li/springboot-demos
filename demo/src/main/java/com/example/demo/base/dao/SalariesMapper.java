package com.example.demo.base.dao;

import com.example.demo.base.vo.Salaries;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

public interface SalariesMapper {
    int deleteByPrimaryKey(@Param("empNo") Integer empNo, @Param("fromDate") Date fromDate);

    int insert(Salaries record);

    int insertSelective(Salaries record);

    Salaries selectByPrimaryKey(@Param("empNo") Integer empNo, @Param("fromDate") Date fromDate);

    int updateByPrimaryKeySelective(Salaries record);

    int updateByPrimaryKey(Salaries record);
}