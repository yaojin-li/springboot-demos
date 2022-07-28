package com.example.demo.utils;

/**
 * @Description: --------------------------------------
 * @ClassName: LogUtils.java
 * @Date: 2020/11/2 17:31
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/

import com.example.demo.enums.LogEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils  {

    /**
     * 获取日志logger
     *
     * @return
     */
    public static Logger getLogger() {
        return LoggerFactory.getLogger(LogEnum.NORMAL.getCategory());
    }

    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
    }


}