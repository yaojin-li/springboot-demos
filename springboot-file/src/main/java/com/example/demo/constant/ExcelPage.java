package com.example.demo.constant;

/**
 * @Description: 前端需要导出或导入Excel的页面名称
 * --------------------------------------
 * @ClassName: ExcelPage.java
 * @Date: 2021/3/3 18:43
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public enum ExcelPage {

    BORROWER_INFO("borrower_info"),

    ;

    private String name;

    ExcelPage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
