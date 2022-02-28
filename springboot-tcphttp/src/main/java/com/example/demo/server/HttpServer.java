package com.example.demo.server;

import com.sun.net.httpserver.spi.HttpServerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * @Description: --------------------------------------
 * @ClassName: HttpServer.java
 * @Date: 2022/2/25 0025  15:13
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class HttpServer {
    /**
     * 最大HTTP监听数量
     */
    private static Integer MAX_HTTP_HANDLER = 100;

    /**
     * HTTP模式时，本地HTTP监听端口
     */
    private static Integer LOCAL_HTTP_PORT = 8888;

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void startHttpServer() {
        try {
            HttpServerProvider provider = HttpServerProvider.provider();
            com.sun.net.httpserver.HttpServer httpServer = provider.createHttpServer(
                    new InetSocketAddress(LOCAL_HTTP_PORT), MAX_HTTP_HANDLER);

            httpServer.setExecutor(null);
            httpServer.start();
            LOGGER.info("HTTP 服务开起完成，端口：" + LOCAL_HTTP_PORT);
        } catch (Exception e) {
            LOGGER.error("HTTP 服务开启失败", e);
        }
    }

}
