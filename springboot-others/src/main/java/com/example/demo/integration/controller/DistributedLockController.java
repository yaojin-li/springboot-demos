package com.example.demo.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 基于redis使用Spring Integration实现分布式锁
 * --------------------------------------
 * @ClassName: DistributedLockController.java
 * @Date: 2021/12/27 0027  9:55
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@RequestMapping("lock")
public class DistributedLockController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockController.class);

    private final RedisLockRegistry redisLockRegistry;

    /*** 通过构造函数注入 **/
    @Autowired
    public DistributedLockController(RedisLockRegistry redisLockRegistry) {
        this.redisLockRegistry = redisLockRegistry;
    }

    @Autowired
    private ZookeeperLockRegistry zookeeperLockRegistry;

    @GetMapping("redis")
    public void test() {
        new Thread(() -> {
            obtainRedisLock("a");
        }).start();
        new Thread(() -> {
            obtainRedisLock("b");
        }).start();
        new Thread(() -> {
            obtainRedisLock("c");
        }).start();
    }

    public void obtainRedisLock(String threadName) {
        Lock lock = redisLockRegistry.obtain("redis");
        try {
            // 尝试在指定时间内加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                LOGGER.info(String.format("lock is ready......[%s] get lock", threadName));
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (Exception e) {
            LOGGER.error("obtain lock error", e);
        } finally {
            lock.unlock();
        }
    }

    @GetMapping("zookeeper")
    public void obtainZkLock(String threadName) {
        Lock lock = zookeeperLockRegistry.obtain("zookeeper");
        try {
            // 尝试在指定时间内加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                LOGGER.info(String.format("lock is ready......[%s] get lock", threadName));
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (Exception e) {
            LOGGER.error("obtain lock error", e);
        } finally {
            lock.unlock();
        }
    }


}
