package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.demo.annotation.MethodLog;
import com.example.demo.utils.FileUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;

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

    /**
     * 上传单个文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @MethodLog
    public Object upload(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String name = file.getOriginalFilename();
        request.getSession().setAttribute("upFile", name);
        String fileSavePath = FileUtils.getFileUploadPath(fileUploadPath);
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
            request.getSession().setAttribute("upFile", name);
            String fileSavePath = FileUtils.getFileUploadPath(fileUploadPath);
            try {
                file.transferTo(new File(fileSavePath + name));
            } catch (Exception e) {
                log.error("上传文件出错！", e);
            }
            map.put(name, IdWorker.getIdStr());
        }
        return map;
    }

}















