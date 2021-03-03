package com.example.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.constant.ExcelPage;
import com.example.demo.excel.BorrowerInfoExcel;
import com.example.demo.excel.BorrowerInfoFields;
import com.example.demo.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
     * 下载Excel文件
     * 注：
     * 前端请求中的 Headers 中指定 Content-Type
     * Content-Type: application/x-www-form-urlencoded
     * */
    @RequestMapping(value = "/downloadExcelInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object downloadExcelInfo(@RequestPart("pageName") String pageName) {
        //
        if (StringUtils.isEmpty(pageName)){
            return null;
        }

        try {
            if (pageName.equals(ExcelPage.BORROWER_INFO.getName())){
                // 1.获取数据
                List<Map<String, Object>> result = excelService.listMaps();
                // 2.写入Excel
                new BorrowerInfoExcel().exportData(result, BorrowerInfoFields.fileName,
                        BorrowerInfoFields.titles, BorrowerInfoFields.columns);
            }else {
                return "error";
            }
        } catch (Exception e) {
            log.error(String.format("下载[%s]Excel文件时，写入Excel数据异常！", pageName), e);
        }
        return null;
    }

}




