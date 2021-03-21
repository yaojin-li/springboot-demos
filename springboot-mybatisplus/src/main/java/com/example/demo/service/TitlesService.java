package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.entity.Titles;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: TitlesService.java
 * @Date: 2021/03/10 22:58:38
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface TitlesService extends IService<Titles> {

    Object selectByMap(Map<String, Object> map);

    Object selectByObj(Titles titles);

    Object paramBasicType(Integer no, String title);

    Object paramList(List<String> list);

    Object queryCompare(Map<String, Object> map);
}
