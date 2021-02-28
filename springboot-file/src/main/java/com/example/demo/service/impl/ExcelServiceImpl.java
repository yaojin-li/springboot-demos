package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.dao.ExcelInfoMapper;
import com.example.demo.base.vo.ExcelInfo;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
public class ExcelServiceImpl extends ServiceImpl<ExcelInfoMapper, ExcelInfo> implements ExcelService {

    @Autowired
    private ExcelInfoMapper excelInfoMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        return excelInfoMapper.selectMaps(null);
    }

    /**
     * 读取Excel数据
     *
     * @param name         文件名称
     * @param fileSavePath 文件保存路径
     * @return result      Excel数据
     */
    @Override
    public JSONObject readExcelData(String name, String fileSavePath) {
        JSONObject result = new JSONObject();
        if (name != null) {
            File file = new File(fileSavePath + name);
            try {
                JSONObject excelData = FileUtil.readExcel(file);
                result.put("excelData", excelData);
                result.put("size", excelData.size());
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
