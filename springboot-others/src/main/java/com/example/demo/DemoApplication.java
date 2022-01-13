package com.example.demo;

//import com.example.demo.elect.ElectLeaderService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Autowired
//    ElectLeaderService electLeaderService;

    @Override
    public void run(String... args) throws Exception {
        // 选取主节点
//         electLeaderService.startElect();
    }
}
