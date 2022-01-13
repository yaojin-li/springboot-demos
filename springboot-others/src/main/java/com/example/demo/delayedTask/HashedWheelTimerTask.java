package com.example.demo.delayedTask;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 时间轮算法实现延迟任务
 * --------------------------------------
 * @ClassName: HashedWheelTimerTask.java
 * @Date: 2021/9/30 14:28
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class HashedWheelTimerTask {
    static class MyTimerTask implements TimerTask {
        boolean flag;

        public MyTimerTask(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("要去数据库删除订单了。。。。");
            this.flag = false;
        }
    }

    public static void main(String[] args) {
        MyTimerTask timerTask = new MyTimerTask(true);
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);

        int i = 1;
        while (timerTask.flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(i + "秒过去了");
            i++;
        }
    }

}
