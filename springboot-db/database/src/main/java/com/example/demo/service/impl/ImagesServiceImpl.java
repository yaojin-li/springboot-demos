package com.example.demo.service.impl;

import com.example.demo.base.dao.ImagesMapper;
import com.example.demo.base.vo.Images;
import com.example.demo.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: --------------------------------------
 * @ClassName: ImagesService.java
 * @Date: 2020/11/4 19:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public Images selectAllImages() {
        return imagesMapper.selectByPrimaryKey(Long.valueOf(1));
    }
}
