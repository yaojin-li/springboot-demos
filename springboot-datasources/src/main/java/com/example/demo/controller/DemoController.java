package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.vo.Videos;
import com.example.demo.base.vo.Images;
import com.example.demo.service.ImagesService;
import com.example.demo.service.VideosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 本地数据源测试
     * http://localhost:8098/localTest?id=1
     */
    @RequestMapping("/localTest")
    @MethodLog
    public String localTest(@RequestParam int id) {
        Videos videos = videosService.selectByPrimaryKey(id);
        log.info(String.format("localTest info...%s", JSONObject.toJSONString(videos)));
        return JSONObject.toJSONString(videos);
    }


    /**
     * 读库测试
     * http://localhost:8098/slaveReadTest?id=1
     */
    @RequestMapping("/slaveReadTest")
    @MethodLog
    public String slaveReadTest(@RequestParam int id) {
        Images images = imagesService.selectByPrimaryKey(id);
        log.info(String.format("slaveReadTest...%s", JSONObject.toJSONString(images)));
        return JSONObject.toJSONString(images);
    }


    /**
     * 主库测试
     * http://localhost:8098/masterCUDTest?id=1&remark=备注测试
     */
    @RequestMapping("/masterCUDTest")
    @MethodLog
    public int masterCUDTest(@RequestParam int id, @RequestParam String remark) {
        int masterUpdateRes = imagesService.updateByPrimaryKeySelective(id, remark);
        log.info(String.format("masterUpdateRes...%s", masterUpdateRes));
        return masterUpdateRes;
    }


}
