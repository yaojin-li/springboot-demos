package com.example.demo.utils;

import java.net.InetAddress;

/**
 * @Description: --------------------------------------
 * @ClassName: IPUtil.java
 * @Date: 2021/12/6 0006  10:44
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class IPUtil {

    public static String getLocalHostIdentifier() {
        return getLocalHostName() + ":" + getLocalIp();
    }

    public static String getLocalHostName() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "localhost";
    }

    public static String getLocalIp() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    public static void main(String[] args) {
        System.out.println(getLocalHostIdentifier());
    }

}
