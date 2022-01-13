package com.example.demo.paramStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * @Description: 获取其他类定义的静态变量
 * --------------------------------------
 * @ClassName: OtherThreadStore.java
 * @Date: 2021/12/3 0003  9:47
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@RequestMapping("/other")
public class OtherThreadStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadStore.class);

    /**
     * 1. 通过类的静态方法获取（public类型的参数）
     * */
    @RequestMapping("/otherGetParam")
    @ResponseBody
    public String getParam() {
        String temp = new ThreadStore().PARAMS;
        LOGGER.info(String.format("OtherDemo获取public类型参数PARAMS[%s]", temp));
        LOGGER.info(String.format("通过静态方法获取参数params[%s]", new ThreadStore().getParams()));
        return temp;
    }

    /**
     * 2. 通过反射获取（public类型的参数）
     */
    @RequestMapping("/otherGetParamByReflect")
    @ResponseBody
    public String getParam2() throws Exception {
        Class clazz = Class.forName("com.example.ThreadStore.paramStorage.ThreadStore");
        // 获取public类型的参数（一般使用类静态方法获取）
        Field params = clazz.getField("PARAMS");
        LOGGER.info(String.format("OtherDemo，反射获取public类型参数PARAMS[%s]", params.get(ThreadStore.class)));
        return params.get(ThreadStore.class).toString();
    }

    /**
     * 3. 通过反射获取（private类型的参数）
     */
    @RequestMapping("/otherGetParamByReflect2")
    @ResponseBody
    public String getParam3() throws Exception {
        Class<ThreadStore> c = ThreadStore.class;
        // 获取私有变量
        Field field = c.getDeclaredField("PARAMS");
        // 消除语言访问检查
        field.setAccessible(true);
        // 获取私有变量的值
        String value = (String) field.get(c);
        LOGGER.info(String.format("OtherDemo，反射获取private类型参数PARAMS[%s]", value));
        return value;
    }

}
