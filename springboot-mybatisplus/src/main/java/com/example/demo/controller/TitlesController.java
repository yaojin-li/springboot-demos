package com.example.demo.controller;


import com.example.demo.base.entity.Titles;
import com.example.demo.service.TitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: TitlesController.java
 * @Date: 2021/03/10 22:58:38
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@RestController
@RequestMapping("/titles")
public class TitlesController {

    @Autowired
    private TitlesService titlesService;

    /**
     * map多个对象传参查询
     * map传递参数，直接在sql中取出key即可 parameterType="map"
     * */
    @RequestMapping("/paramMap")
    public Object paramMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("title", "Staff");
        map.put("emp_no", "10005");
        return titlesService.selectByMap(map);
    }

    /**
     * 对象传参
     * 对象传递参数，直接在sql中取出对象的属性即可 parameterType="object"
     * */
    @RequestMapping("/paramObject")
    public Object paramObject(){
        Titles titles = new Titles();
        titles.setTitle("Staff");
        titles.setEmpNo(10005);
        return titlesService.selectByObj(titles);
    }


    /**
     * 基本类型传参 & 模糊查询
     * 只有一个基本类型参数情况下，可以直接在sql中取值
     * */
    @RequestMapping("/paramBasicType")
    public Object paramBasicType(){
        Integer no = new Integer(10005);
        String title = "taf";
        return titlesService.paramBasicType(no, title);
    }

    /**
     * List 传参
     * */
    @RequestMapping("/paramList")
    public Object paramList(){
        List<String> list = new ArrayList<>();
        list.add("10005");
        list.add("10007");
        return titlesService.paramList(list);
    }


    /**
     * 比较参数作为条件
     * */
    @RequestMapping("/queryCompare")
    public Object queryCompare(){
        Map<String, Object> map = new HashMap<>();
        map.put("title", "Staff");
        map.put("emp_no", "10005");
        return titlesService.queryCompare(map);
    }

}
