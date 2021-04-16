package com.example.demo.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: --------------------------------------
 * @ClassName: AppController.java
 * @Date: 2021/4/16 11:57
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RequestMapping("/app")
@RestController
@Slf4j
@Api(value = "APP端AppController", tags = "AppController")
public class AppController {

    /**
     * 测试APP端swagger
     * */
    @ApiOperation(value = "app接口-测试", notes = "app接口-测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
    })
    @PostMapping(value = "/test")
    @ResponseBody
    public String test(@RequestParam String encodeParams){
        log.info(encodeParams);
        return encodeParams;
    }

}
