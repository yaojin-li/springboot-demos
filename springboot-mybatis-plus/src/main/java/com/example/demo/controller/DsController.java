package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import com.example.demo.base.entity.Employees;
import com.example.demo.base.entity.Images;
import com.example.demo.base.entity.Videos;
import com.example.demo.service.EmployeesService;
import com.example.demo.service.ImagesService;
import com.example.demo.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试mybatis plus多数据源
 * --------------------------------------
 * @ClassName: DsController.java
 * @Date: 2021/3/28 11:44
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class DsController {

    @Autowired
    public ImagesService imagesService;

    @Autowired
    public EmployeesService employeesService;

    @Autowired
    public VideosService videosService;

    /**
     * 测试mybatis plus多数据源
     * */
    @RequestMapping("/ds")
    @MethodLog
    public void ds(){
        System.out.println("=========master db test==========");
        Images masterDbTest = imagesService.masterDbTest(1);
        System.out.println(masterDbTest);

        System.out.println("=========mysql db test==========");
        Videos Videos = videosService.mysqlDbTest(2);
        System.out.println(Videos);

        System.out.println("=========others db test==========");
        Employees othersDbTest = employeesService.othersDbTest(10005);
        System.out.println(othersDbTest);

    }
}
