package com.example.demo.leaf;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.service.SnowflakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: SnowflakeTest.java
 * @Date: 2022/9/26 21:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class SnowflakeTest {

    //雪花算法ID
    @Autowired
    private SnowflakeService snowflakeService;

    //获取snowflake分布式ID
    //id  这个参数是没有意义的，只是为了和号段模式的接口统一，所以要传一个参数，随意定义
    @RequestMapping("snowflakeTest")
    public String getSnowflakeId() {
        Result r = snowflakeService.getId("id");
        return r.toString();
    }



}

