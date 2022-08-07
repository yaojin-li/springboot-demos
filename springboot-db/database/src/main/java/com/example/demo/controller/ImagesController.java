package com.example.demo.controller;

import com.example.demo.base.vo.Images;
import com.example.demo.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: ImagesController.java
 * @Date: 2020/11/4 19:06
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    @RequestMapping("/imgInfo")
    public void imgInfo(){
        Images images = imagesService.selectAllImages();
        System.out.println(images);
    }

}
