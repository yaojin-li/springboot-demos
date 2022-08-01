package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: --------------------------------------
 * @ClassName: WebSocketServer.java
 * @Date: 2021/7/20 14:51
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@ServerEndpoint(value = "/ws/asset")
@Component
@Log4j2
public class WebSocketServer {

    // 基本类型原子类
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> SESSION_SET = new CopyOnWriteArraySet<Session>();

    @PostConstruct
    public void init() {
        System.out.println("WebSocket 加载");
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     **/
    @OnOpen
    public void onOpen(Session session) {
        // 添加session
        SESSION_SET.add(session);
        int count = ONLINE_COUNT.incrementAndGet();
        log.info(String.format("有连接加入，当前连接数为：[%s]", count));
        sendMessage(session, "连接成功");
    }

    /**
     * 连接关闭调用的方法
     *
     * @param session
     **/
    @OnClose
    public void onClose(Session session) {
        // 移除session
        SESSION_SET.remove(session);
        int count = ONLINE_COUNT.decrementAndGet();
        log.info(String.format("有连接关闭，当前连接数为：[%s]", count));
    }

    /**
     * 刷新浏览器，session会变化
     *
     * @param session
     * @param message
     **/
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：{}", message);
        sendMessage(session, "收到消息，消息内容：" + message);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void broadCastInfo(String message) throws IOException {
        for (Session session : SESSION_SET) {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }


    /**
     * 指定Session发送消息
     *
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void sendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : SESSION_SET) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            sendMessage(session, message);
        } else {
            log.warn("未找到指定ID的会话：{}", sessionId);
        }
    }


}




