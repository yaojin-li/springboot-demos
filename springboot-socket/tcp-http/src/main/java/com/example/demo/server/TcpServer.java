package com.example.demo.server;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TCP请求转http请求
 * --------------------------------------
 * @ClassName: TcpServer.java
 * @Date: 2022/2/25 0025  15:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class TcpServer extends Thread {
    /**
     * 最大TCP/HTTP读取缓冲大小
     */
    private static Integer MAX_BUFFER = 20480;

    /**
     * 转发HTTP服务器地址
     */
    private static String HTTP_SERVER = "http://localhost:8085/testTcp";

    protected Socket socket;
    private static final Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);

    @Override
    public void run() {
        try {
            // 1. 接收TCP请求
            InputStream inputStream = socket.getInputStream();
            // 缓冲
            byte[] buffer = new byte[MAX_BUFFER];
            int length = inputStream.read(buffer);
            //
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = buffer[i];
            }
            LOGGER.info("TCP server 接收到数据：" + new String(data));
            socket.shutdownInput();

            // 2. 转化为http请求
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 此处为误差post，可设置有参
            HttpPost httpPost = new HttpPost(HTTP_SERVER);
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

            //
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                // 响应状态失败
                if (200 != statusCode) {
                    LOGGER.error("http请求响应状态异常，响应内容：" + response);
                    return;
                }

                // 获取响应实体
                LOGGER.info("http正常返回：" + response);
                HttpEntity responseEntity = response.getEntity();
                byte[] en = toByteArray(responseEntity.getContent());
                byte[] de = Base64Utils.decode(en);
                OutputStream outputStream = socket.getOutputStream();
                // TODO 可能涉及乱码问题
                outputStream.write(de);
                outputStream.flush();
                socket.shutdownOutput();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOGGER.info("完成TCP交互，退出socket");
        } catch (Exception e) {
            LOGGER.error("接受TCP数据异常", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void startTcpServer(int port, Class obj) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            LOGGER.info("TCP server 监听：" + port);
            while (true) {
                Socket socket = null;
                try {
                    //
                    socket = serverSocket.accept();
                    LOGGER.info("建立新连接...");
                    TcpServer server = (TcpServer) obj.newInstance();
                    server.socket = socket;
                    server.start();
                } catch (Exception e) {
                    LOGGER.error("建立TCP连接错误", e);
                    socket.close();
                }
            }
        } catch (Exception e) {
            LOGGER.error("启动TCP监听错误", e);
        }

    }

    /**
     * InputStream 转 byte
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }


}
