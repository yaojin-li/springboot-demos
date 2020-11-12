package com.example.demo.base.dao;

import com.example.demo.base.vo.Images;

public interface ImagesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Images record);

    int insertSelective(Images record);

    Images selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Images record);

    int updateByPrimaryKey(Images record);
}