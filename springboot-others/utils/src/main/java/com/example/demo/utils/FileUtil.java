package com.example.demo.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: --------------------------------------
 * @ClassName: FileUtil.java
 * @Date: 2021/2/22 21:31
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Slf4j
public class FileUtil {
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

    /**
     * 获取系统临时路径
     */
    public static String getTempPath() {
        return System.getProperty("java.io.tmpdir");
    }


    /**
     * 读取导入的Excel数据--优化中
     */
    public static JSONObject readExcel(File file) {
        JSONObject result = new JSONObject();
        //  try-with-resources
        try (InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
             HSSFWorkbook workbook = new HSSFWorkbook(inputStream)){

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

                // 第一行标题
                HSSFRow firstRow = sheet.getRow(0);

                JSONArray sheetData = new JSONArray();
                // 第一行标题 排除
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    HSSFRow row = sheet.getRow(rowIndex);
                    // 空行继续
                    if (ObjectUtils.isNull(row)) {
                        continue;
                    }

                    Map<String, Object> rowData = new ConcurrentHashMap<>();
                    boolean hasValue = false;

                    // 遍历每行数据
                    for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
                        HSSFCell cell = row.getCell(columnIndex);
                        Object value = "";

                        if (ObjectUtils.isNotNull(cell)) {
                            switch (cell.getCellTypeEnum()) {
                                case STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    // 数字单元格类型
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        // 日期
                                        value = new SimpleDateFormat(DateTimeUtil.DATA_PATTERN).format(cell.getDateCellValue());
                                    } else {
                                        // 数字
                                        value = cell.getNumericCellValue();
                                    }
                                    break;
                                case FORMULA:
                                    // 公式单元格类型
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        // 日期
                                        value = new SimpleDateFormat(DateTimeUtil.DATA_PATTERN).format(cell.getDateCellValue());
                                    } else {
                                        // 数字
                                        value = cell.getNumericCellValue();
                                    }
                                    break;
                                default:
                                    value = "";
                            }
                        }

                        // 获取第一行标题对应的枚举
                        if (ObjectUtils.isNotEmpty(firstRow.getCell(columnIndex))) {
                            rowData.put(firstRow.getCell(columnIndex).getStringCellValue(), value);
                            hasValue = true;
                        } else {
                            log.error(String.format("[%s]文件标题中存在空值！", file.getName()));
                            throw new NullPointerException();
                        }
                    }

                    if (hasValue) {
                        sheetData.add(rowData);
                    }
                }
                if (!sheetData.isEmpty()) {
                    result.put(sheet.getSheetName(), sheetData);
                }
            }
        } catch (Exception e) {
            log.error(String.format("读取Excel文件[%s]异常！", file.getName()), e);
        }
        return result;
    }


    /**
     * 读取Excel数据
     *
     * @param name         文件名称
     * @param fileSavePath 文件保存路径
     * @return result      Excel数据
     */
    public static JSONObject readExcelData(String name, String fileSavePath) {
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
