package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.entity.Videos;
import com.example.demo.service.VideosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 图片相关 前端控制器
 * </p>
 *
 * @author lixj
 * @since 2021-06-28
 */
@RestController
@Slf4j
@Api(value = "服务端SwaggerController", tags = "SwaggerController")
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    VideosService videosService;

    /**
     * 测试服务端端swagger
     */
    @ApiOperation("测试get请求")
    @GetMapping("/helloGet/{name}")
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
        return videosService.list().size();
    }


    @ApiOperation("测试-分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始", required = true, dataType = "Integer" ),
            @ApiImplicitParam(name = "size", value = "个数", required = true, dataType = "Integer" )
    })
    @GetMapping("/page/{current}/{size}")
    public List<Videos> page(@PathVariable Integer current, @PathVariable Integer size) {
        return videosService.queryTest(current, size);
    }

}

