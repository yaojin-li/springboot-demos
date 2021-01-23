package com.example.demo.items.processor;


import org.springframework.batch.item.ItemProcessor;

/**
 * @Description: --------------------------------------
 * @ClassName: TxtItemProcessor.java
 * @Date: 2021/1/23 16:22
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TxtItemProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String s) throws Exception {
        return s + "---------PROCESSED";
    }
}
