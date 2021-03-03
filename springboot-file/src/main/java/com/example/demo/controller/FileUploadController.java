package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.demo.annotation.MethodLog;
import com.example.demo.base.vo.BorrowerInfo;
import com.example.demo.constant.BorrowerInfoDbConstant;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.FileUtil;
import com.example.demo.utils.JsonUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @Description: --------------------------------------
 * @ClassName: FileUploadController.java
 * @Date: 2021/2/22 20:45
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Log4j2
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Value("${file.uploadPath}")
    private String fileUploadPath;

    @Autowired
    private ExcelService excelService;

    /**
     * 上传单个文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @MethodLog
    public Object upload(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String name = file.getOriginalFilename();
        request.getSession().setAttribute("upFile", name);
        String fileSavePath = FileUtil.getFileUploadPath(fileUploadPath);
        try {
            file.transferTo(new File(fileSavePath + name));
        } catch (Exception e) {
            log.error("上传单个文件出错！", e);
        }
        HashMap<String, Object> map = new HashMap<>();
        // 记录fileID
        map.put(name, IdWorker.getIdStr());
        return map;
    }


    /**
     * 上传多个文件
     */
    @RequestMapping(value = "/uploadMulti", method = RequestMethod.POST)
    @ResponseBody
    @MethodLog
    public Object uploadMulti(@RequestPart("file") MultipartFile[] files, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            String fileSavePath = FileUtil.getFileUploadPath(fileUploadPath);
            try {
                file.transferTo(new File(fileSavePath + name));
            } catch (Exception e) {
                log.error("上传文件出错！", e);
            }
            map.put(name, IdWorker.getIdStr());
        }
        return map;
    }


    /**
     * 获取上传成功的Excel数据并插入数据库
     */
    @RequestMapping("/getUploadData")
    @ResponseBody
    public String getUploadData(HttpServletRequest request, @RequestBody JSONObject params) {
        // 获取上传成功的数据
        String fileName = (String) request.getSession().getAttribute("upFile");
        JSONObject uploadData = FileUtil.readExcelData(fileName, fileUploadPath);

        List<BorrowerInfo> res = new ArrayList<>();
        JSONObject data = (JSONObject) uploadData.get("excelData");
        Iterator sheetIter = data.entrySet().iterator();

        // 遍历每个sheet页
        while (sheetIter.hasNext()) {
            Map.Entry dataEntry = (Map.Entry) sheetIter.next();
            String dataStr = ((JSONArray) dataEntry.getValue()).toJSONString();
            JSONArray rowsArray = JSONObject.parseArray(dataStr);
            // 遍历每个sheet页中的数据
            for (Object oneRow : rowsArray) {
                // 转换：sheet页的标题行与数据库中字段对应
                JSONObject jsonObj = JsonUtil.changeJsonObj((JSONObject) oneRow, BorrowerInfoDbConstant.BORROWER_INFO_DB_KEY_MAP);
                // 将每行数据转换为dto
                BorrowerInfo excelInfo = JSON.toJavaObject(changeDtoValue(jsonObj), BorrowerInfo.class);
                res.add(excelInfo);
            }
        }
        // 批量插入
        excelService.saveBatch(res, 1000);
        return "success";
    }

    /**
     * 转换sheet中的值为字典项
     * 定义枚举类型或数组
     */
    public static JSONObject changeDtoValue(JSONObject jsonObject) {
        jsonObject.put("sex", jsonObject.get("sex").equals("男") ? "1" : "0");
        return jsonObject;
    }


}















