package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @Description: --------------------------------------
 * @ClassName: RedisToolUtil.java
 * @Date: 2021/12/1 0001  19:46
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class RedisToolUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisToolUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 共享锁默认时长（毫秒）：60秒
     */
    private static final long TIMEOUT = 60000;

    /**
     * 超时时长（毫秒）：1秒
     */
    private static final long OVERTIME = 1000;

    /**
     * 获取锁
     * @param key
     * @param requestId
     * @param timeOut   锁超时时间，单位毫秒
     * @param overTime  尝试获取锁的最长时间，单位毫秒
     * @param retryNum  可重试的次数
     * @return  是否获取到锁
     **/
    public boolean lock(String key, String requestId, Long timeOut, Long overTime, Integer retryNum) {
        if (timeOut == null) {
            timeOut = TIMEOUT;
        }
        if (overTime == null) {
            overTime = OVERTIME;
        }
        if (retryNum == null) {
            retryNum = Integer.MAX_VALUE;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < retryNum; i++) {
            boolean locked = redisTemplate.opsForValue().setIfAbsent(key, requestId, timeOut, TimeUnit.MILLISECONDS);
            if (locked) {
                return true;
            }
            if ((System.currentTimeMillis() - startTime) > overTime) {
                return false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.error("线程被中断" + Thread.currentThread().getId(), e);
            }
        }
        return false;
    }


}
