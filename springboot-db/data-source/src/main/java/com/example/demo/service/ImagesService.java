package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.vo.Images;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: ImagesService.java
 * @Date: 2021/03/09 12:14:47
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface ImagesService {

    Images selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Integer id, String remark);

}
