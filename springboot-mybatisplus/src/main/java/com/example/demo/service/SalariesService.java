package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.entity.Salaries;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: SalariesService.java
 * @Date: 2021/03/10 23:03:32
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface SalariesService extends IService<Salaries> {

    List<Salaries> queryTest(Integer current, Integer size);

    Cursor<Salaries> cursorTest(int limit);

    List<Map<String, Object>> selectInfo(Page<Salaries> page, Map<String, Object> condition);

}
