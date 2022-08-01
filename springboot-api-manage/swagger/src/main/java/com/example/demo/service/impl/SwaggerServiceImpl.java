package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.base.dao.SalariesMapper;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.SwaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: MybatisPlusServiceImpl.java
 * @Date: 2020/11/22 10:22
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class SwaggerServiceImpl implements SwaggerService {

    @Autowired
    private SalariesMapper salariesMapper;

    @Override
    public int selectAllCount() {
        return salariesMapper.selectAllCount();
    }

    @Override
    public List<Salaries> queryTest(Integer current, Integer size) {
        IPage<Salaries> page = new Page<>(current, size);

        IPage<Salaries> iPage = salariesMapper.selectPage(page, null);

        return iPage.getRecords();
    }
}
