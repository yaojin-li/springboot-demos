package com.example.demo.excel;

/**
 * @Description: --------------------------------------
 * @ClassName: BorrowerInfoFields.java
 * @Date: 2021/3/3 18:49
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class BorrowerInfoFields {

    public static String fileName = "";

    /**
     * excel 列名。后续可将名称与关系维护至数据库中。
     * */
    public static String[] titles = new String[]{"序号", "姓名", "性别", "出借数量", "密码", "创建时间", "更新时间", "备注"};

    /**
     * 列名对应字段名。与获取数据字段名一致。
     * */
    public static String[] columns = new String[]{"id", "name", "sex", "borrow_num", "password", "create_time", "update_time", "note"};



}
