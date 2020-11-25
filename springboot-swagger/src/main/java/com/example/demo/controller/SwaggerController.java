package com.example.demo.controller;

import com.example.demo.base.vo.Salaries;
import com.example.demo.service.SwaggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RestController
@Api(value = "测试-swagger测试 控制器", tags = "SwaggerController")
public class SwaggerController {

    @Autowired
    public SwaggerService swaggerService;

    @ApiOperation("测试-hello get 映射方法")
    @GetMapping({"/", "helloGet"})
    @ApiImplicitParam(name = "name", value = "起始", required = true, dataType = "String", paramType = "path")
    public String helloGet(@RequestParam String name) {
        return name;
    }

    @ApiOperation("测试-hello post 映射方法")
    @PostMapping({"/", "helloPost"})
    public String helloPost() {
        return "helloPost";
    }

    @ApiOperation("测试-获取salaries表的总记录数")
    @PostMapping("/swaggerTest")
    public int swaggerTest() {
        return swaggerService.selectAllCount();
    }

    @ApiOperation("测试-分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "个数", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping("/queryTest")
    public List<Salaries> queryTest(@RequestParam Integer current, @RequestParam Integer size) {
        return swaggerService.queryTest(current, size);
    }


}
