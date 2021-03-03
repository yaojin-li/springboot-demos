package com.example.demo.excel;

import com.example.demo.constant.Constants;
import com.example.demo.utils.AbstractExcelUtil;
import com.example.demo.utils.DateTimeUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: BorrowerInfoExcel.java
 * @Date: 2021/3/3 18:59
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class BorrowerInfoExcel extends AbstractExcelUtil {
    /**
     * 设置格式
     */
    @Override
    public List<XSSFCellStyle> createStyle(XSSFWorkbook wb) {
        List<XSSFCellStyle> cellStyles = new ArrayList<>();
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);
        cellStyles.add(style);
        return cellStyles;
    }

    /**
     * 转换、设置cell中的值
     */
    @Override
    public void convertValue(XSSFCell cell, int columnIndex, Object val, List<XSSFCellStyle> cellStyle) {
        if (val != null) {
            // TODO LXJ 性别转换。指定具体的列，可考虑将Excel中的列字段罗列至枚举类型遍历。
            if (2 == columnIndex) {
                cell.setCellValue(Constants.SEX[(Integer) (val)]);
            } else if (5 == columnIndex || 6 == columnIndex){
                // 时间格式转换
                cell.setCellValue(DateTimeUtil.dateFormat(String.valueOf(val)));
            } else {
                cell.setCellValue(String.valueOf(val));

            }
        }
    }
}
