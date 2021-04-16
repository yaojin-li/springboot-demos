package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.SwaggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/app")
@RestController
@Slf4j
@Api(value = "服务端SwaggerController", tags = "SwaggerController")
public class SwaggerController {

    @Autowired
    SwaggerService swaggerService;

    /**
     * 测试服务端端swagger
     */
    @ApiOperation("测试get请求")
    @GetMapping("/helloGet")
    @ApiImplicitParam(name = "name", value = "起始", required = true, dataType = "String")
    public String helloGet(@PathVariable String name) {
        return name;
    }


    @ApiOperation("测试post请求")
    @PostMapping("/helloPost")
    public void helloPost(@RequestBody JSONObject params) {
        log.info("测试post请求>>>>>>>>>>>>>>" + params.getString(""));
    }


    @ApiOperation("测试-获取salaries表的总记录数")
    @GetMapping("/account")
    public int account() {
        return swaggerService.selectAllCount();
    }


    @ApiOperation("测试-分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "个数", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping("/page")
    public List<Salaries> page(@RequestParam Integer current, @RequestParam Integer size) {
        return swaggerService.queryTest(current, size);
    }


}
