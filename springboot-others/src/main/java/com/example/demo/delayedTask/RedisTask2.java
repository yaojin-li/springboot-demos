package com.example.demo.delayedTask;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: --------------------------------------
 * @ClassName: RedisTask2.java
 * @Date: 2021/9/30 13:43
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class RedisTask2 {
    private static final int threadNum = 10;
    private static CountDownLatch latch = new CountDownLatch(threadNum);

    static class DelayMessage implements Runnable {

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RedisTask redisTask = new RedisTask();
            redisTask.consumerDelayMessage();
        }
    }

    public static void main(String[] args) {
        RedisTask redisTask = new RedisTask();
        redisTask.productionDelayMessage();
        // 模拟多个线程消费
        for (int i = 0; i < threadNum; i++) {
            new Thread(new DelayMessage()).start();
            latch.countDown();
        }
    }


}














