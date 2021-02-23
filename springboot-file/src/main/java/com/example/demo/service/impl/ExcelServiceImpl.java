package com.example.demo.service.impl;

import com.example.demo.base.dao.ExcelInfoMapper;
import com.example.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: ExcelServiceImpl.java
 * @Date: 2021/2/22 21:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelInfoMapper excelInfoMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        return excelInfoMapper.selectMaps(null);
    }
}
