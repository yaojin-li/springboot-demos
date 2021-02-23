package com.example.demo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

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

    @RequestMapping(value = "/readExcelData", method = RequestMethod.POST)
    @ResponseBody
    public Object readExcelData(HttpServletRequest request, @RequestBody JSONObject params) {
        String name = (String) request.getSession().getAttribute("upFile");
        String fileSavePath = FileUtils.getFileUploadPath(fileUploadPath);
        if (name != null) {
            File file = new File(fileSavePath + name);
            try {
//                ImportParams params = new ImportParams();
//                params.setTitleRows(1);
//                params.setHeadRows(1);
//                List result = ExcelImportUtil.importExcel(file, ExcelItem.class, params);
//
//                LayuiPageInfo returns = new LayuiPageInfo();
//                returns.setCount(result.size());
//                returns.setData(result);
//                return returns;
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}




