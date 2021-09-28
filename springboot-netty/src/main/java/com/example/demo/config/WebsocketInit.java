package com.example.demo.config;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description: websocket初始化器
 * --------------------------------------
 * @ClassName: WebsocketInit.java
 * @Date: 2021/9/28 17:03
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/

public class WebsocketInit {

    @Resource
    private WebsocketInit websocketInit;

    @Value("${websocket.port}")
    private Integer port;

    @Async
    public void init() {

        // bossGroup 连接线程组，接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // workGroup 工作线程组，处理网络IO读写
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }



    }


}
