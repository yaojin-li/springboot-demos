package com.example.demo.service.Impl;

import com.example.demo.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Description: --------------------------------------
 * @ClassName: EmailServiceImpl.java
 * @Date: 2020/12/1 19:39
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage messages = new SimpleMailMessage();
        messages.setFrom(from);
        messages.setTo(to);
        messages.setText(content);
        messages.setSubject(subject);

        try {
            mailSender.send(messages);
            logger.info(String.format("简单邮件已发送。[%s]", messages.toString()));
        } catch (Exception e) {
            logger.error("邮件发送异常！", e);
        }

    }
}
