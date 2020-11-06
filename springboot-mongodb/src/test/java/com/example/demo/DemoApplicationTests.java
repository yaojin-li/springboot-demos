package com.example.demo;

import com.example.demo.base.vo.Images;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        List<Images> result = mongoTemplate.findAll(Images.class);
        System.out.println(result);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getClass());
            System.out.println(result.get(i).getId());
            System.out.println(result.get(i).getSize());
            System.out.println(result.get(i).getTitle());
            System.out.println(result.get(i).getType());
        }
    }

}
