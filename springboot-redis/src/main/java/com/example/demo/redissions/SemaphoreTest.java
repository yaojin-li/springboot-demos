package com.example.demo.redissions;

import com.example.demo.utils.RedisUtil;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis信号量
 * --------------------------------------
 * @ClassName: SemaphoreTest.java
 * @Date: 2022/1/24 0024  14:44
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class SemaphoreTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreTest.class);
    /*** 信号量实例 **/
    public static final String SEMAPHORE_NAME_KEY = "redis:semaphore:name";
    /*** 初始化信号量个数 **/
    public static final Integer INIT_PERMITS_AMOUNT = 20;
    /*** 获取指定个数的信号量 **/
    public static final Integer PERMITS_AMOUNT = 5;
    /*** 等待时间 **/
    public static final Long WAIT_TIME = 3L;

    @Autowired
    RedissonClient redissonClient;

    @PostConstruct
    public void init() {
        //
        RSemaphore semaphore = redissonClient.getSemaphore(SEMAPHORE_NAME_KEY);
        int availablePermits = semaphore.availablePermits();
        if (availablePermits > 0) {
            LOGGER.info(String.format("信号量初始化时原始值为[%s]...", availablePermits));
            return;
        }
        if (semaphore.trySetPermits(INIT_PERMITS_AMOUNT)) {
            LOGGER.info(String.format("线程[%s]初始化指定[%s]个信号量完成...", Thread.currentThread().getId(), INIT_PERMITS_AMOUNT));
        } else {
            LOGGER.error(String.format("线程[%s]初始化信号量异常！！", Thread.currentThread().getId()));
        }
    }

    /**
     * 获取信号量
     */
//    @Scheduled(cron = "0 */1 * * * ?")
    public void semaphoreAcquire() {
        RSemaphore semaphore = redissonClient.getSemaphore(SEMAPHORE_NAME_KEY);
        try {
            // 获取一个信号量（redis中信号量-1），信号量为0时线程阻塞，拿到信号量继续执行。
            semaphore.acquire();
            //
            int availablePermits = semaphore.availablePermits();
            LOGGER.info(String.format("线程[%s]获取信号量，执行业务逻辑...共计[%s]个信号量",
                    Thread.currentThread().getId(), availablePermits));
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放信号量
     */
//    @Scheduled(cron = "0 */2 * * * ?")
    public void semaphoreRelease() {
        RSemaphore semaphore = redissonClient.getSemaphore(SEMAPHORE_NAME_KEY);
        try {
            // 释放一个信号量（redis中信号量+1）
            semaphore.release();
            //
            int availablePermits = semaphore.availablePermits();
            LOGGER.info(String.format("线程[%s]释放信号量...共计[%s]个信号量",
                    Thread.currentThread().getId(), availablePermits));
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分布式限流
     */
//    @Scheduled(cron = "0 */3 * * * ?")
    public void semaphoreTryAcquire() {
        RSemaphore semaphore = redissonClient.getSemaphore(SEMAPHORE_NAME_KEY);
        try {
            // 尝试获得PERMITS_AMOUNT个permit信号量，等待时间WAIT_TIME
            boolean acquire = semaphore.tryAcquire(PERMITS_AMOUNT, WAIT_TIME, TimeUnit.SECONDS);
            if (acquire) {
                int availablePermits = semaphore.availablePermits();
                // 获取到信号量，执行业务
                LOGGER.info(String.format("线程[%s]获取[%s]个信号量，共[%s]个信号量，剩余[%s]个信号量，执行业务逻辑...",
                        Thread.currentThread().getId(), PERMITS_AMOUNT, availablePermits, (availablePermits - PERMITS_AMOUNT)));
            } else {
                // 进行限流
                LOGGER.error("未获得信号量，限流返回...");
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}





