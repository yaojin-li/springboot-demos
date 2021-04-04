package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.dao.TitlesMapper;
import com.example.demo.base.entity.Titles;
import com.example.demo.service.TitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: TitlesServiceImpl.java
 * @Date: 2021/03/10 22:58:38
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@Service
public class TitlesServiceImpl extends ServiceImpl<TitlesMapper, Titles> implements TitlesService {

    @Autowired
    private TitlesMapper titlesMapper;

    @Override
    public Object selectByMap(Map<String, Object> map) {
        /**
         * 封装方法
         * */
        List<Titles> titles = titlesMapper.selectByMap(map);

        /**
         * map传参
         * */
        return titlesMapper.selectByNewMap(map);
    }

    @Override
    public Object selectByObj(Titles titles) {
        /**
         * 对象传参
         * */
        return titlesMapper.selectByObj(titles);
    }

    @Override
    public Object paramBasicType(Integer no, String title) {
        /**
         * 基本类型传参
         * */
        return titlesMapper.paramBasicType(no, title);
    }

    @Override
    public Object paramList(List<String> list) {
        /**
         * list 传参
         * */
        return titlesMapper.paramList(list);
    }

    @Override
    public Object queryCompare(Map<String, Object> map) {
        /**
         * 比较参数作为条件
         * */
        return titlesMapper.queryCompare(map);
    }

    @Override
    public Object paramLike(Integer no, String title) {
        /**
         * 模糊搜索
         * 在传参中加入模糊匹配符。
         * 注：不在XML中设置%，防止SQL注入。
         * */
        String titleLike = "%" + title + "%";
        return titlesMapper.paramLike(no, titleLike);
    }

}
