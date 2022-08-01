package com.example.demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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

    /**
     * 对接 后台 docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("服务端对接接口")
                .apiInfo(apinfo())
                .select()
                // 采用包注解的方式确定要显示的接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 对接 app docket
     */
    @Bean
    public Docket appDocketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("app端对接接口")
                .apiInfo(apinfo())
                .select()
                // 指定包路径的方式确定要显示的接口
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.api"))
                .paths(PathSelectors.any())
                .build();
    }

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