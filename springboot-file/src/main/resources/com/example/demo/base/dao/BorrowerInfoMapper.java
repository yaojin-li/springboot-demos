package com.example.demo.base.dao;

import com.example.demo.base.vo.BorrowerInfo;

public interface BorrowerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BorrowerInfo record);

    int insertSelective(BorrowerInfo record);

    BorrowerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BorrowerInfo record);

    int updateByPrimaryKey(BorrowerInfo record);
}