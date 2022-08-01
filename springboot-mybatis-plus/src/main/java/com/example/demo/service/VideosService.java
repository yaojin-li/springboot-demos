package com.example.demo.service;

import com.example.demo.base.entity.Videos;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: VideosService.java
 * @Date: 2021/03/28 11:43:05
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface VideosService extends IService<Videos> {

    Videos mysqlDbTest(int i);

}
