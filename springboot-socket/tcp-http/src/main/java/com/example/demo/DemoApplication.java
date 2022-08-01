package com.example.demo;

import com.example.demo.server.HttpServer;
import com.example.demo.server.TcpServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Value("${local.tcp.port}")
    String localTcpPort;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            // 没有指定启动方式
            System.out.println("USAGE: java -jar tcp2http.jar [HTTP/TCP]");
        } else if (args[0].toUpperCase().equals("HTTP")) {
            // http方式启动应用
            HttpServer.startHttpServer();
        } else if (args[0].toUpperCase().equals("TCP")) {
            // TCP方式启动应用
            TcpServer.startTcpServer(Integer.parseInt(localTcpPort), TcpServer.class);
        } else {
            // 参数非法
            System.out.println("参数非法: java -jar tcp2http.jar [HTTP/TCP]");
        }
    }
}
