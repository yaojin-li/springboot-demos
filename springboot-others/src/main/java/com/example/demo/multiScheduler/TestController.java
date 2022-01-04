//package com.example.demo.multiScheduler;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Description: 测试@scheduled注解多线程配置
// * --------------------------------------
// * @ClassName: TestController.java
// * @Date: 2021/12/29 0029  15:33
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@RestController
//public class TestController {
//    private Logger logger = LoggerFactory.getLogger(TestController.class);
//    private int taskOneCount = 0;
//    private int taskTwoCount = 0;
//    private int taskThreeCount = 0;
//
//    @Async
//    @Scheduled(cron = "0/2 * *  * * ?")
//    public void taskOne() {
//        logger.info(String.format("task 1 running...[%s]，第[%s]次执行...", Thread.currentThread().getName(), taskOneCount++));
//    }
//
//    @Async
//    @Scheduled(cron = "0/4 * *  * * ?")
//    public void taskTwo() {
//        logger.info(String.format("task 2 running...[%s]，第[%s]次执行...", Thread.currentThread().getName(), taskTwoCount++));
//    }
//
//    @Async
//    @Scheduled(cron = "0/6 * *  * * ?")
//    public void taskThree() {
//        logger.info(String.format("task 3 running...[%s]，第[%s]次执行...", Thread.currentThread().getName(), taskThreeCount++));
//    }
//}
