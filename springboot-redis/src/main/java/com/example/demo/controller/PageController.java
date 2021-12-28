package com.example.demo.controller;

import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 测试缓存分页
 * --------------------------------------
 * @ClassName: PageController.java
 * @Date: 2021/12/28 0028  17:26
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class PageController {

    @Autowired
    RedisUtil redisUtil;

    @Scheduled(cron = "0 */1 * * * ?")
    public void savePage() {
        List<Object> list = new ArrayList<>();
        list.add("a");
        list.add(9);
        list.add('c');
        redisUtil.saveCache("test-key", list, 30000);
    }

}





