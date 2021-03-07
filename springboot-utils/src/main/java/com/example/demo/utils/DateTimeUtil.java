package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: --------------------------------------
 * @ClassName: DateTimeUtil.java
 * @Date: 2021/2/25 23:33
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class DateTimeUtil {

    /**
     * 日期格式-年月日
     * */
    public static String DATA_PATTERN = "yyyy-MM-dd";

    /**
     * 格式化日期
     * yyyy-MM-dd
     * */
    public static String dateFormat(String timeStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_PATTERN);
        Date date = new Date();
        try {
            date = dateFormat.parse(timeStr);
        }catch (Exception e){

        }
        return dateFormat.format(date);
    }

}
