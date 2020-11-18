package com.example.demo.base.dao.local;


import com.example.demo.base.vo.local.Videos;

public interface VideosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);
}