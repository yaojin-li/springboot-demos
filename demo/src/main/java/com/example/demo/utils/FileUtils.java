package com.example.demo.utils;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @Description: --------------------------------------
 * @ClassName: FileUtils.java
 * @Date: 2021/2/22 21:31
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Slf4j
public class FileUtils {
    /**
     * 获取文件上传路径(用于头像和富文本编辑器)
     */
    public static String getFileUploadPath(String fileUploadPath) {
        if (StringUtils.isEmpty(fileUploadPath)) {
            log.error("文件上传路径为空！");
            return getTempPath();
        } else {
            //判断有没有结尾符
            if (!fileUploadPath.endsWith(File.separator)) {
                fileUploadPath = fileUploadPath + File.separator;
            }

            //判断目录存不存在
            File file = new File(fileUploadPath);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                if (mkdirs) {
                    return fileUploadPath;
                } else {
                    log.error("文件上传路径为空！");
                    return getTempPath();
                }
            } else {
                return fileUploadPath;
            }
        }
    }

    public static String getTempPath() {
        return System.getProperty("java.io.tmpdir");
    }
}
