package com.example.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.constant.ExcelPage;
import com.example.demo.excel.BorrowerInfoExcel;
import com.example.demo.excel.BorrowerInfoFields;
import com.example.demo.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Controller
@Slf4j
@RequestMapping("/excel")
public class ExcelController {

    @Value("${file.uploadPath}")
    private String fileUploadPath;

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/excelDemo")
    public String excelDemo() {
        return "excelDemo";
    }

    /**
     * 下载Excel文件
     * 注：
     * 1.前端请求中的 Headers 中指定 Content-Type；
     * Content-Type: application/x-www-form-urlencoded
     * 2.请求的body中，from-data中传参pageName；
     * 3.页面下载的文件名称为中文，postman等访问接口下载的文件名称为编码后的结果。
     */
    @RequestMapping(value = "/downloadExcelInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object downloadExcelInfo(HttpServletRequest request) {
        //
        String excelPage = request.getParameter("excelPage");
        if (StringUtils.isEmpty(excelPage)) {
            return null;
        }

        try {
            // 判断需要下载的excel对应的页面和数据库
            if (excelPage.equals(ExcelPage.BORROWER_INFO.getName())) {
                // 1.获取数据
                List<Map<String, Object>> result = excelService.listMaps();
                // 2.写入Excel
                new BorrowerInfoExcel().exportData(result, BorrowerInfoFields.fileName,
                        BorrowerInfoFields.titles, BorrowerInfoFields.columns);
            } else {
                return "error";
            }
        } catch (Exception e) {
            log.error(String.format("下载[%s]Excel文件时，写入Excel数据异常！", excelPage), e);
        }
        return null;
    }

}




