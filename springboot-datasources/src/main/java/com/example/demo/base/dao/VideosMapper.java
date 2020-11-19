package com.example.demo.base.dao;


import com.example.demo.base.vo.Videos;

public interface VideosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);
}