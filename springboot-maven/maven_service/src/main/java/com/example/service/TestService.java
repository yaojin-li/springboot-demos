package com.example.service;

import com.example.dao.TestDao;

/**
 * @Description: --------------------------------------
 * @ClassName: TestService.java
 * @Date: 2021/4/7 11:41
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TestService {

    public static void testService(){
        System.out.println("service test...");

        TestDao.testDao();
    }

}
