package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.dao.SalariesMapper;
import com.example.demo.base.entity.Salaries;
import com.example.demo.service.SalariesService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: SalariesServiceImpl.java
 * @Date: 2021/03/10 23:03:32
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
@Service
@Log4j2
public class SalariesServiceImpl extends ServiceImpl<SalariesMapper, Salaries> implements SalariesService {
    @Autowired
    private SalariesMapper salariesMapper;

    @Override
    public List<Salaries> queryTest(Integer current, Integer size) {
        IPage<Salaries> page = new Page<>(current, size);

        IPage<Salaries> iPage = salariesMapper.selectPage(page, null);
        log.info("总页数:"+iPage.getPages());
        log.info("总记录数:"+iPage.getTotal());

        return iPage.getRecords();
    }

    @Override
    public Cursor<Salaries> cursorTest(int limit) {
        return salariesMapper.cursorTest(limit);
    }

    @Override
    public List<Map<String, Object>> selectInfo(Page<Salaries> page, Map<String, Object> condition) {
        return  salariesMapper.selectInfo(page, condition);
    }

}
