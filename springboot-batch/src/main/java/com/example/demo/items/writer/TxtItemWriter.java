package com.example.demo.items.writer;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @Description: --------------------------------------
 * @ClassName: TxtItemWriter.java
 * @Date: 2021/1/23 16:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TxtItemWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) throws Exception {
        for (String item : list) {
            System.out.println(item);
        }
    }
}
