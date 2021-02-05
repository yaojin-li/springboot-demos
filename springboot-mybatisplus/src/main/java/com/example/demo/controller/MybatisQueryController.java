package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.MybatisPlusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: MybatisQueryController.java
 * @Date: 2021/2/4 18:13
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
@Log4j2
public class MybatisQueryController {

    @Autowired
    public MybatisPlusService mybatisPlusService;

    @MethodLog
    @RequestMapping(value = "/mybatisQuery", method = RequestMethod.POST)
    public Object mybatisQuery(@RequestBody JSONObject params) {
        int current = Integer.valueOf(params.getString("current"));
        int limit = Integer.valueOf(params.getString("limit"));
        Page<Salaries> page = new Page<>(current, limit);
        Map<String, Object> condition = JSON.toJavaObject(params, Map.class);
        List<Map<String, Object>> result = mybatisPlusService.selectInfo(page, condition);
        page.setRecords(result);
        return
    }


}
