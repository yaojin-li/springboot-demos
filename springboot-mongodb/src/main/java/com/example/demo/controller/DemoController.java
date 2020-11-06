package com.example.demo.controller;

import com.example.demo.base.vo.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: DemoController.java
 * @Date: 2020/11/6 19:33
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class DemoController {

    /**
     * 使用 springframework 中的MongoDB模板创建连接
     * */
    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("/mongodbTest")
    public void mongodbTest() {
        List<Images> result = mongoTemplate.findAll(Images.class);
        System.out.println(result);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getClass());
            System.out.println(result.get(i).getId());
            System.out.println(result.get(i).getSize());
            System.out.println(result.get(i).getTitle());
            System.out.println(result.get(i).getType());
        }

    }

}
