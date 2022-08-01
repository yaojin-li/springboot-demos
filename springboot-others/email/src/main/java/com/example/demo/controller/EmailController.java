package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: --------------------------------------
 * @ClassName: EmailController.java
 * @Date: 2020/12/1 19:40
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/sendSimpleEmail")
    @ResponseBody
    public String sendSimpleEmail(){
        emailService.sendSimpleMail("lixj_zj@163.com","主题：测试普通邮件","内容：测试第一封邮件");
        return "success";
    }

    @RequestMapping("/sendHtmlMail")
    @ResponseBody
    public String sendHtmlMail(){
        emailService.sendHtmlMail("lixj_zj@163.com","主题：html邮件","<h1>内容：第一封html邮件</h1>");
        return "success";
    }

    @RequestMapping("/sendAttachmentsMail")
    @ResponseBody
    public String sendAttachmentsMail(){
        emailService.sendAttachmentsMail("lixj_zj@163.com","主题：含附件邮件","附件邮件","C:\\Users\\User\\Desktop\\error.txt");
        return "success";
    }

}
