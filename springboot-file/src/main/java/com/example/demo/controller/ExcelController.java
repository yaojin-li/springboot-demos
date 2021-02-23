package com.example.demo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.vo.ExcelInfo;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: ExcelController.java
 * @Date: 2021/2/23 19:15
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@Slf4j
@RequestMapping("/excel")
public class ExcelController {

    @Value("${file.uploadPath}")
    private String fileUploadPath;

    @Autowired
    private ExcelService excelService;

    /**
     * 获取Excel数据
     * */
    @RequestMapping(value = "/readExcelData", method = RequestMethod.POST)
    @ResponseBody
    public Object readExcelData(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String name = (String) request.getSession().getAttribute("upFile");
        String fileSavePath = FileUtils.getFileUploadPath(fileUploadPath);
        if (name != null) {
            File file = new File(fileSavePath + name);
            try {
                JSONObject excelData = FileUtils.readExcel(file);
                result.put("excelData", excelData);
                result.put("size", excelData.size());
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 下载Excel文件
     * 列表数据
     * */
    @RequestMapping(value = "/downloadExcelInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object downloadExcelInfo(HttpServletRequest request) {
        // 1.获取数据
        List<Map<String, Object>> result = excelService.selectAll();

        // 2.写入Excel

        return null;
    }

}




