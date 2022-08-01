package com.example.demo.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.base.vo.Videos;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: VideosMapper.java
 * @Date: 2021/03/09 12:10:29
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface VideosMapper extends BaseMapper<Videos> {
    int deleteByPrimaryKey(Integer id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);
}
