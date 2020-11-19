package com.example.demo.service;

import com.example.demo.base.vo.Images;

/**
 * @Description: --------------------------------------
 * @ClassName: ImagesService.java
 * @Date: 2020/11/18 18:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface ImagesService {

    Images selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Integer id, String remark);

}
