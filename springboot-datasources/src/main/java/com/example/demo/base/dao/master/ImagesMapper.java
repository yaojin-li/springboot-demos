package com.example.demo.base.dao.master;


import com.example.demo.base.vo.master.Images;

public interface ImagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Images record);

    int insertSelective(Images record);

    Images selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Images record);

    int updateByPrimaryKey(Images record);
}