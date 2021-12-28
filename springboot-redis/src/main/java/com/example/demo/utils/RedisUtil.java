package com.example.demo.utils;

import com.example.demo.page.PageData;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Description: --------------------------------------
 * @ClassName: RedisUtil.java
 * @Date: 2021/12/1 0001  19:46
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class RedisUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JedisPool jedisPool;

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
     *
     * @param key
     * @param requestId
     * @param timeOut   锁超时时间，单位毫秒
     * @param overTime  尝试获取锁的最长时间，单位毫秒
     * @param retryNum  可重试的次数
     * @return 是否获取到锁
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

    /**
     * 保存缓存
     *
     * @param key     根据策略生成key
     * @param list    需要加载到缓存中的数据集合。list.add(index, data)设置有序
     * @param timeout 缓存过期时间
     */
    public void saveCache(final String key, List<?> list, Integer timeout) {
        if (list.isEmpty()) {
            LOGGER.info("saveCache list 为空");
        }
        Jedis redis = null;
        try {
            redis = jedisPool.getResource();
            Pipeline pipeline = redis.pipelined();
            // 开起事务
            pipeline.multi();
            String setValue = "";
            for (int i = 0; i < list.size(); i++) {
                Object[] dataForRow = (Object[]) list.get(i);
                for (int j = 0; j < dataForRow.length; j++) {
                    if (ObjectUtils.isEmpty(dataForRow[j])) {
                        dataForRow[j] = " ";
                    }
                    setValue += dataForRow[j].toString() + ",";
                }
                pipeline.zadd(key, (double) i, setValue);
            }
            // 设置过期时间
            pipeline.expire(key, timeout);
            // 提交事务
            pipeline.exec();
            // 同步相应并关闭管道
            pipeline.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 从缓存中获取数据
     *
     * @param pageData 分页工具类
     * @param key
     * @return 缓存分页数据
     */
    public PageData<Object> getPageDataFromCache(PageData<Object> pageData, String key) {

        return pageData;
    }

}
