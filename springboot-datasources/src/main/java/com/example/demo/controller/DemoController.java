package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.vo.local.Videos;
import com.example.demo.base.vo.master.Images;
import com.example.demo.service.ImagesService;
import com.example.demo.service.VideosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: DemoController.java
 * @Date: 2020/11/18 18:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@Log4j2
public class DemoController {

    @Autowired
    public VideosService videosService;

    @Autowired
    public ImagesService imagesService;

    @RequestMapping("/localTest")
    @MethodLog
    public String localTest(){
        Videos videos = videosService.selectByPrimaryKey(1);
        log.info(String.format("localTest...%s", JSONObject.toJSONString(videos)));
        return JSONObject.toJSONString(videos);
    }

    @RequestMapping("/slaveCUDTest")
    @MethodLog
    public String slaveReadTest(){
        Images images = imagesService.selectByPrimaryKey(1);
        log.info(String.format("slaveReadTest...%s", JSONObject.toJSONString(images)));
        return JSONObject.toJSONString(images);
    }

}
