package com.example.demo.enums;

/**
 * @Description: 定义不同类型的业务日志
 * --------------------------------------
 * @ClassName: LogEnum.java
 * @Date: 2020/11/2 17:29
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public enum LogEnum {
    NORMAL("normal"),

    EXCEPTION("exception"),

    ;

    private String category;

    LogEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}