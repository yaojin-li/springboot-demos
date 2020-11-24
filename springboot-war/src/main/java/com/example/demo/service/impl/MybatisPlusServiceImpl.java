package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.base.dao.SalariesMapper;
import com.example.demo.base.vo.Salaries;
import com.example.demo.service.MybatisPlusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
@Log4j2
public class MybatisPlusServiceImpl implements MybatisPlusService {

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
        log.info("总页数:"+iPage.getPages());
        log.info("总记录数:"+iPage.getTotal());

        return iPage.getRecords();
    }
}
