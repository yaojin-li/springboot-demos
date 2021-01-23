package com.example.demo.items.reader;

import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * @Description: --------------------------------------
 * @ClassName: TxtItemReader.java
 * @Date: 2021/1/23 16:56
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TxtItemReader {

    public ItemReader<String> read() {
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        try {
            File file = new File("E:\\workspace\\springboot-demos\\springboot-batch\\src\\main\\resources\\data\\test.txt");
            reader.setResource(new FileSystemResource(file));
            reader.setLineMapper(new LineMapper<String>() {
                @Override
                public String mapLine(String line, int lineNum) throws Exception {
                    return line;
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return reader;
    }

}
