package com.example.demo.service;

import com.example.demo.base.entity.Images;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: ImagesService.java
 * @Date: 2021/03/28 10:56:11
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface ImagesService extends IService<Images> {

    Images masterDbTest(int i);
}
