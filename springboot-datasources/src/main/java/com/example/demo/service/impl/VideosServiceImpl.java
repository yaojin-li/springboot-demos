package com.example.demo.service.impl;

import com.example.demo.base.dao.local.VideosMapper;
import com.example.demo.base.vo.local.Videos;
import com.example.demo.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: --------------------------------------
 * @ClassName: VideosServiceImpl.java
 * @Date: 2020/11/18 18:26
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class VideosServiceImpl implements VideosService {

    @Autowired
    public VideosMapper videosMapper;

    @Override
    public Videos selectByPrimaryKey(Integer id) {
        return videosMapper.selectByPrimaryKey(1);
    }
}
