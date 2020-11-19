package com.example.demo.service.impl;

import com.example.demo.annotation.DataSourceSwitch;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.dao.ImagesMapper;
import com.example.demo.base.vo.Images;
import com.example.demo.enums.DataSourceEnum;
import com.example.demo.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: --------------------------------------
 * @ClassName: ImagesServiceImpl.java
 * @Date: 2020/11/18 18:09
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    public ImagesMapper imagesMapper;

    /**
     * 从库read操作
     * */
    @Override
    @DataSourceSwitch(DataSourceEnum.SLAVE)
    public Images selectByPrimaryKey(Integer id) {
        return imagesMapper.selectByPrimaryKey(1);
    }


    /**
     * 主库CUD操作
     * */
    @Override
    @DataSourceSwitch(DataSourceEnum.MASTER)
    public int updateByPrimaryKeySelective(Integer id, String remark){
        Images images = new Images();
        images.setId(id);
        images.setRemark(remark);
        return imagesMapper.updateByPrimaryKeySelective(images);
    }
}
