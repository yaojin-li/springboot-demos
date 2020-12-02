package com.example.demo.service;

/**
 * @Description: --------------------------------------
 * @ClassName: EmailService.java
 * @Date: 2020/12/1 19:39
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface EmailService {

    void sendSimpleMail(String to, String subject, String content);

}
