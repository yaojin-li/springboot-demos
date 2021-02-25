package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ExcelInfoService;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.FileUtil;
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
     */
    @RequestMapping(value = "/readExcelData", method = RequestMethod.POST)
    @ResponseBody
    public Object readExcelData(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String name = (String) request.getSession().getAttribute("upFile");
        String fileSavePath = FileUtil.getFileUploadPath(fileUploadPath);
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

    /**
     * 下载Excel文件
     * 列表数据
     */
    @RequestMapping(value = "/downloadExcelInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object downloadExcelInfo(HttpServletRequest request) {
        // 1.获取数据
        List<Map<String, Object>> result = excelService.selectAll();
        String fileName = "测试数据导出";
        // Excel列名
        String[] titles = new String[]{"序号", "姓名", "性别", "出借数量", "密码", "创建时间", "更新时间", "备注"};
        // 列名对应字段名。与获取数据字段名一致。
        String[] columns = new String[]{"id", "name", "sex", "borrow_num", "password", "create_time", "update_time", "note"};

        // 2.写入Excel
        try {
            new ExcelInfoService().exportData(result, fileName, titles, columns);
        } catch (Exception e) {
            log.error("下载Excel文件时，写入Excel数据异常！", e);
        }
        return null;
    }

}




