//package com.example.demo.paramStorage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Description: 测试进程间储存变量
// * --------------------------------------
// * @ClassName: Demo.java
// * @Date: 2021/12/3 0003  9:07
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@RestController
//@RequestMapping("/demo")
//public class Demo {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);
//    /**
//     * public类型通过类的静态方法、简单反射获取
//     * */
//    public static String PARAMS = null;
//
//    /**
//     * private类型通过反射（消除语言访问检查）获取
//     * */
//    private static String PARAMS2 = null;
//
//    @RequestMapping("/genParam")
//    @ResponseBody
//    public void genParam() {
//        String param = "测试参数param...";
//        LOGGER.info(String.format("Demo生成参数param[%s]", param));
//        PARAMS = param;
//    }
//
//    @RequestMapping("/getParam")
//    @ResponseBody
//    public String getParam() {
//        LOGGER.info(String.format("Demo获取参数PARAMS[%s]", PARAMS));
//        return PARAMS;
//    }
//
//    @RequestMapping("/updateParam")
//    @ResponseBody
//    public void updateParam() {
//        String param = "测试参数param...更新";
//        LOGGER.info(String.format("Demo更新...参数param[%s]", param));
//        PARAMS = param;
//    }
//
//    public String getParams(){
//        return PARAMS;
//    }
//
//}
