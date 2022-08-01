package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.base.entity.Employees;
import com.example.demo.base.entity.Images;
import com.example.demo.base.dao.ImagesMapper;
import com.example.demo.service.ImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: ImagesServiceImpl.java
 * @Date: 2021/03/28 10:56:11
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @Autowired
    public ImagesMapper imagesMapper;

    /**
     * 测试mybatis plus多数据源
     * */
    @Override
    @DS("master")
    public Images masterDbTest(int id){
        return imagesMapper.selectById(id);
    }
}
