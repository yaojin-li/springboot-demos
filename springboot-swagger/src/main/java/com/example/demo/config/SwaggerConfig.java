package com.example.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * @Description: --------------------------------------
 * @ClassName: SwaggerConfig.java
 * @Date: 2020/11/24 19:01
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apinfo());
    }

    /**
     * 定义多个 docket
     * */
//    @Bean
//    public Docket docketOther() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("docketOther")
//                .apiInfo(apinfo());
//    }

    private ApiInfo apinfo() {
        Contact contact = new Contact(
                "demo user",
                "https://blog.csdn.net/HeyShHeyou",
                "lixj_zj@163.com");

        return new ApiInfo("demo user 的api文档",
                "Api Documentation",
                "1.0",
                "https://blog.csdn.net/HeyShHeyou",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}