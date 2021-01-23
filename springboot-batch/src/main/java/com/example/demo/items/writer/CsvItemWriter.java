package com.example.demo.items.writer;

import com.example.demo.dto.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: CsvItemWriter.java
 * @Date: 2021/1/23 16:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class CsvItemWriter {

    /**
     * 写数据时指定数据源
     * */
    public JdbcBatchItemWriter write(DataSource dataSource) {
        // 使用的JdbcBatchltemWriter则是通过JDBC将数据写出到一个关系型数据库中。
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        // 配置使用的数据源
        writer.setDataSource(dataSource);
        // 配置数据插入SQL，注意占位符的写法是":属性名"
        writer.setSql("insert into user(id,username,sex) " +
                "values(:id,:username,:sex)");
        // 最后通过BeanPropertyItemSqlParameterSourceProvider实例将实体类的属性和SQL中的占位符一一映射
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }

}
