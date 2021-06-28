package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.entity.Videos;

import java.util.List;

/**
 * <p>
 * 图片相关 服务类
 * </p>
 *
 * @author lixj
 * @since 2021-06-28
 */
public interface VideosService extends IService<Videos> {
    List<Videos> queryTest(Integer current, Integer size);

}
