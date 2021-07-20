package com.example.demo.controller;

import com.example.demo.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @Description: --------------------------------------
 * @ClassName: WebSocketController.java
 * @Date: 2021/7/19 19:38
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Controller
@RequestMapping("/webSocket")
public class WebSocketController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 群发消息内容
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/sendAll")
    @ResponseBody
    public String sendAllMessage(@RequestParam(required = true) String message) {
        try {
            WebSocketServer.broadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }


    @GetMapping(value = "/sendOne")
    @ResponseBody
    public String sendOneMessage(@RequestParam(required = true) String message,
                                 @RequestParam(required = true) String id) {
        try {
            WebSocketServer.sendMessage(message, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
