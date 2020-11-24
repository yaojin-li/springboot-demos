package com.example.demo.controller;

import com.example.demo.annotation.MethodLog;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.MybatisPlusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: MybatisPlusController.java
 * @Date: 2020/11/22 10:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@RestController
public class MybatisPlusController {

    @Autowired
    public MybatisPlusService mybatisPlusService;

    @MethodLog
    @RequestMapping("/mybatisPlusTest")
    public int mybatisPlusTest(){
        return mybatisPlusService.selectAllCount();
    }

    /**
     * http://192.168.100.130:8095/wardemo-0.0.1-SNAPSHOT/queryTest?current=2000000&size=3
     * http://IP:端口/服务名/请求映射
     * 端口：Tomcat默认端口，server.xml配置
     * 服务名：webapps中的服务文件夹名称
     * */
    @RequestMapping("/queryTest")
    @MethodLog
    public List<Salaries> queryTest(@RequestParam Integer current, @RequestParam Integer size) {
        return mybatisPlusService.queryTest(current, size);
    }


}
