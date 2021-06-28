package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.dao.VideosMapper;
import com.example.demo.base.entity.Videos;
import com.example.demo.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 图片相关 服务实现类
 * </p>
 *
 * @author lixj
 * @since 2021-06-28
 */
@Service
public class VideosServiceImpl extends ServiceImpl<VideosMapper, Videos> implements VideosService {

    @Autowired
    VideosMapper mapper;

    @Override
    public List<Videos> queryTest(Integer current, Integer size) {
        IPage<Videos> page = new Page<>(current, size);
        IPage<Videos> iPage = mapper.selectPage(page, null);
        return iPage.getRecords();
    }
}
