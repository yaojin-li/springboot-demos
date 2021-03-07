package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: --------------------------------------
 * @ClassName: StringUtil.java
 * @Date: 2021/2/25 22:58
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class StringUtil {

    /**
     * 转换字符串首字母大写
     *
     * @param str 字符串
     */
    public static String firstToUpperCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
