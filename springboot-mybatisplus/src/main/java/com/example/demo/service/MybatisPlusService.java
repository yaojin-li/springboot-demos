package com.example.demo.service;

import com.example.demo.base.vo.Salaries;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

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

    List<Salaries> queryTest(Integer current, Integer size);

    Cursor<Salaries> cursorTest(int limit);

}
