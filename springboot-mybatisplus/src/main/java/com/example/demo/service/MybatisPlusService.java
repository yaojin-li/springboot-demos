package com.example.demo.service;

import com.example.demo.base.vo.Salaries;

/**
 * @Description: --------------------------------------
 * @ClassName: MybatisPlusService.java
 * @Date: 2020/11/22 10:21
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface MybatisPlusService {

    int selectAllCount();

    Salaries queryTest(Integer current, Integer size);

}
