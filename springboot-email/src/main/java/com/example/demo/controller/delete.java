package com.example.demo.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: delete.java
 * @Date: 2020/12/12 11:33
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class delete {
    public static void main(String[] args) {
        int[] num = new int[]{47742,70351,109346,90369,74489,92545,202497,295715,208620,162285,387366};
        DecimalFormat df=new DecimalFormat("0.0000");
        float sum = 0;
        for (int i = 0; i < num.length-1; i++) {
            int sub = num[i + 1] - num[i];
            String res = df.format((float)sub / num[i]);
            System.out.println(res);
            sum += Float.valueOf(res);
        }
        System.out.println(sum);
        System.out.println(df.format(sum / 12));
    }


}
