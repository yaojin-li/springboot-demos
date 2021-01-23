package com.example.demo.items.reader;

import com.example.demo.dto.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * @Description: --------------------------------------
 * @ClassName: CsvItemReader.java
 * @Date: 2021/1/23 22:30
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class CsvItemReader {

    public ItemReader<User> read() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setEncoding("utf-8");
        // 配置data.txt文件的位置。此处配置文件容易产生乱码问题。
        reader.setResource(new ClassPathResource("data/data.txt"));
        // 设置每行数据
        reader.setLineMapper(new DefaultLineMapper<User>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                // setNames方法配置了data.csv文件一共有4列，分别是id、username、以及sex,
                setNames("id", "username", "sex");
                // 配置列与列之间的间隔符（这里是空格）
                setDelimiter(" ");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(User.class);
            }});
        }});
        return reader;
    }
}
