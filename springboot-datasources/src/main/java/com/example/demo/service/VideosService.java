package com.example.demo.service;

import com.example.demo.base.vo.local.Videos;

/**
 * @Description: --------------------------------------
 * @ClassName: VideosService.java
 * @Date: 2020/11/18 18:25
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface VideosService {

    Videos selectByPrimaryKey(Integer id);

}
