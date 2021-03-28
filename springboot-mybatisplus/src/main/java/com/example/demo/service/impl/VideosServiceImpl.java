package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.base.dao.VideosMapper;
import com.example.demo.base.entity.Videos;
import com.example.demo.service.VideosService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: --------------------------------------
 * @ClassName: VideosServiceImpl.java
 * @Date: 2021/03/28 11:43:05
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@Service
public class VideosServiceImpl extends ServiceImpl<VideosMapper, Videos> implements VideosService {


    @Autowired
    public VideosMapper videosMapper;

    /**
     * 测试mybatis plus多数据源
     */
    @Override
    @DS("mysql")
    public Videos mysqlDbTest(int id) {
        return videosMapper.selectById(id);
    }
}
