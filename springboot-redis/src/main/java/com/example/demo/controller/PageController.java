package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.page.PageData;
import com.example.demo.page.User;
import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        list.add("b");
        list.add("c");

        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(99);
        list.add(list2);

        Map<String, User> userMap = new HashMap<>();
        userMap.put("1", new User("张三", 20));
        userMap.put("2", new User("李四", 24));
        list.add(userMap);

        redisUtil.saveCache("test-key", list, 30000);

        PageData<Object> result = redisUtil.getPageDataFromCache(new PageData<>(), "test-key");
        System.out.println(JSON.toJSONString(result));

    }

}





