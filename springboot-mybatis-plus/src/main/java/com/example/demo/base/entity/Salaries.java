package com.example.demo.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Description: Salaries实体类
 * --------------------------------------
 * @ClassName: Salaries.java
 * @Date: 2022/02/15 15:40:54
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */

@Data
@TableName("salaries")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Salaries对象", description = "Salaries对象")
public class Salaries implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("salary")
    private Integer salary;

    @TableField("emp_no")
    private Integer empNo;

    @TableField("col1")
    private Integer col1;

    @TableField("col2")
    private Integer col2;

    @TableField("col3")
    private Integer col3;

    @TableField("col4")
    private Integer col4;

    @TableField("col5")
    private Integer col5;

    @TableField("col6")
    private Integer col6;

    @TableField("col7")
    private Integer col7;

    @TableField("col8")
    private Integer col8;

    @TableField("col9")
    private Integer col9;

    @TableField("col10")
    private Integer col10;

    @TableField("col11")
    private Integer col11;

    @TableField("col12")
    private Integer col12;

    @TableField("col13")
    private Integer col13;

    @TableField("col14")
    private Integer col14;

    @TableField("col15")
    private Integer col15;

    @TableField("col16")
    private Integer col16;

    @TableField("col17")
    private Integer col17;

    @TableField("col18")
    private Integer col18;

    @TableField("col19")
    private Integer col19;

    @TableField("col20")
    private Integer col20;

    @TableField("from_date")
    private LocalDate fromDate;

    @TableField("to_date")
    private LocalDate toDate;


    public static final String SALARY ="salary";

    public static final String EMP_NO ="emp_no";

    public static final String COL1 ="col1";

    public static final String COL2 ="col2";

    public static final String COL3 ="col3";

    public static final String COL4 ="col4";

    public static final String COL5 ="col5";

    public static final String COL6 ="col6";

    public static final String COL7 ="col7";

    public static final String COL8 ="col8";

    public static final String COL9 ="col9";

    public static final String COL10 ="col10";

    public static final String COL11 ="col11";

    public static final String COL12 ="col12";

    public static final String COL13 ="col13";

    public static final String COL14 ="col14";

    public static final String COL15 ="col15";

    public static final String COL16 ="col16";

    public static final String COL17 ="col17";

    public static final String COL18 ="col18";

    public static final String COL19 ="col19";

    public static final String COL20 ="col20";

    public static final String FROM_DATE ="from_date";

    public static final String TO_DATE ="to_date";

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Integer getCol1() {
        return col1;
    }

    public void setCol1(Integer col1) {
        this.col1 = col1;
    }

    public Integer getCol2() {
        return col2;
    }

    public void setCol2(Integer col2) {
        this.col2 = col2;
    }

    public Integer getCol3() {
        return col3;
    }

    public void setCol3(Integer col3) {
        this.col3 = col3;
    }

    public Integer getCol4() {
        return col4;
    }

    public void setCol4(Integer col4) {
        this.col4 = col4;
    }

    public Integer getCol5() {
        return col5;
    }

    public void setCol5(Integer col5) {
        this.col5 = col5;
    }

    public Integer getCol6() {
        return col6;
    }

    public void setCol6(Integer col6) {
        this.col6 = col6;
    }

    public Integer getCol7() {
        return col7;
    }

    public void setCol7(Integer col7) {
        this.col7 = col7;
    }

    public Integer getCol8() {
        return col8;
    }

    public void setCol8(Integer col8) {
        this.col8 = col8;
    }

    public Integer getCol9() {
        return col9;
    }

    public void setCol9(Integer col9) {
        this.col9 = col9;
    }

    public Integer getCol10() {
        return col10;
    }

    public void setCol10(Integer col10) {
        this.col10 = col10;
    }

    public Integer getCol11() {
        return col11;
    }

    public void setCol11(Integer col11) {
        this.col11 = col11;
    }

    public Integer getCol12() {
        return col12;
    }

    public void setCol12(Integer col12) {
        this.col12 = col12;
    }

    public Integer getCol13() {
        return col13;
    }

    public void setCol13(Integer col13) {
        this.col13 = col13;
    }

    public Integer getCol14() {
        return col14;
    }

    public void setCol14(Integer col14) {
        this.col14 = col14;
    }

    public Integer getCol15() {
        return col15;
    }

    public void setCol15(Integer col15) {
        this.col15 = col15;
    }

    public Integer getCol16() {
        return col16;
    }

    public void setCol16(Integer col16) {
        this.col16 = col16;
    }

    public Integer getCol17() {
        return col17;
    }

    public void setCol17(Integer col17) {
        this.col17 = col17;
    }

    public Integer getCol18() {
        return col18;
    }

    public void setCol18(Integer col18) {
        this.col18 = col18;
    }

    public Integer getCol19() {
        return col19;
    }

    public void setCol19(Integer col19) {
        this.col19 = col19;
    }

    public Integer getCol20() {
        return col20;
    }

    public void setCol20(Integer col20) {
        this.col20 = col20;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}