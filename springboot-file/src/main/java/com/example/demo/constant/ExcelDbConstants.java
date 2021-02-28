package com.example.demo.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 定义Excel第一行title与数据库中字段对应关系
 * --------------------------------------
 * @ClassName: ExcelDbConstants.java
 * @Date: 2021/2/28 16:10
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class ExcelDbConstants {

    /**
     * 重载HashMap的一个匿名实现
     * */
    public static Map<String, String> EXCEL_DB_KEY_MAP = new HashMap<String, String>() {
        {
            put("姓名", "name");
            put("性别", "sex");
            put("借入数量", "borrowNum");
            put("密码", "password");
            put("备注", "note");
        }
    };

}
