package com.example.demo.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: --------------------------------------
 * @ClassName: SchedulerTask.java
 * @Date: 2020/11/3 11:39
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class PrintSchedulerTask {

    private int count = 0;

    @Scheduled(cron = "*/3 * * * * ?")
    public void taskProcess(){
        System.out.println("scheduler task runing ..." + (count++));
    }

}
