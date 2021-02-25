package com.example.demo.service;

import com.example.demo.utils.HttpUtil;
import com.example.demo.utils.StringUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: ExcelService.java
 * @Date: 2021/2/22 21:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public abstract class AbstractExcelService {

    private static final String FIREFOX = "firefox";

    public void exportData(List<?> list, String fileName, String[] titles, String[] columns) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        List<XSSFCellStyle> cellStyle = createStyle(wb);
        XSSFSheet sheet = wb.createSheet();
        writeTitle(sheet, titles);
        writeBody(list, sheet, columns, cellStyle);
        doExport(wb, fileName);
    }

    /**
     * 在workbook里面创建cellStyle
     *
     * @param wb workbook
     * @return celleStyle
     */
    public abstract List<XSSFCellStyle> createStyle(XSSFWorkbook wb);


    /**
     * 在sheet页面写入标题
     *
     * @param sheet  sheet页
     * @param titles 标题数组
     */
    private void writeTitle(XSSFSheet sheet, String[] titles) {
        XSSFRow row0 = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            row0.createCell(i).setCellValue(titles[i]);
        }
    }

    /**
     * 写数据
     *
     * @param list      数据
     * @param sheet     sheet页
     * @param columns   字段名
     * @param cellStyle cellStyle
     */
    private void writeBody(List<?> list, XSSFSheet sheet, String[] columns, List<XSSFCellStyle> cellStyle) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                // row=行数加上对应的长度
                XSSFRow row = sheet.createRow(1 + i);
                // 循环对应的列，获取对应的cell单元格数据
                for (int j = 0; j < columns.length; j++) {
                    XSSFCell cell = row.createCell(j);
                    Object item = list.get(i);
                    Object val;
                    if (item instanceof Map) {
                        Map<String, Object> map = (Map<String, Object>) item;
                        val = map.get(columns[j]);
                    } else {
                        Class clazz = item.getClass();
                        Method method = clazz.getDeclaredMethod("get" + StringUtil.firstToUpperCase(columns[j]));
                        val = method.invoke(item);
                    }
                    convertValue(cell, j, val, cellStyle);
                }
            }
        }
    }

    /**
     * 数据自定义转换
     *
     * @param columnIndex 第几列
     * @param cell        当前单元格
     * @param val         原始值
     * @param cellStyle   cell格式
     */
    public abstract void convertValue(XSSFCell cell, int columnIndex, Object val, List<XSSFCellStyle> cellStyle);

    /**
     * 导出Excel文件
     *
     * @param workbook Excel工作簿
     * @param fileName 文件名称
     */
    private void doExport(Workbook workbook, String fileName) throws IOException {
        String agent = HttpUtil.getRequest().getHeader("USER-AGENT").toLowerCase();
        HttpServletResponse response = HttpUtil.getResponse();
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        if (agent.contains(FIREFOX)) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1) + ".xlsx");
        } else {
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");
        }
        workbook.write(response.getOutputStream());
    }

}