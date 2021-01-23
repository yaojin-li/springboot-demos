package com.example.demo.items.processor;


import com.example.demo.dto.User;
import org.springframework.batch.item.ItemProcessor;

/**
 * @Description: --------------------------------------
 * @ClassName: CsvItemProcessor.java
 * @Date: 2021/1/23 16:22
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class CsvItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        // 数据处理，比如将中文性别设置为M/F
        if ("男".equals(user.getSex())){
            user.setSex("M");
        }else {
            user.setSex("F");
        }
        return user;
    }
}
