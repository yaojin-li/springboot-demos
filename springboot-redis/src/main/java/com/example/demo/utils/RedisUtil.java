package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.page.PageData;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.*;
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
            LOGGER.error("saveCache list 为空");
            return;
        }
        Jedis redis = null;
        try {
            redis = jedisPool.getResource();
            Pipeline pipeline = redis.pipelined();
            // 开起事务
            pipeline.multi();
            for (int i = 0; i < list.size(); i++) {
                pipeline.zadd(key, (double) i, JSON.toJSONString(list.get(i)));
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
        if (StringUtils.isEmpty(key)) {
            LOGGER.error("getPageDataFromCache key 为空");
            return pageData;
        }
        Jedis redis = null;
        List<Object> cacheList = new ArrayList<Object>();
        try {
            redis = jedisPool.getResource();
            // 获得总记录数
            Long totalCount = redis.zcard(key);
            if (totalCount <= 0) {
                return pageData;
            }
            // 计算分页
            Integer beginIndex = (pageData.getPageNo() - 1) * pageData.getPageSize();
            Integer endIndex = beginIndex + pageData.getPageSize() - 1;
            if (pageData.getTotalCount() == 0) {
                // 保存总记录数
                pageData.setTotalCount(totalCount.intValue());
            }
            // Sorted Set 返回结果封装为 LinkedHashSet
            Set<String> cacheDataForRow = redis.zrange(key, beginIndex, endIndex);
            cacheList.addAll(cacheDataForRow);
            pageData.setResult(cacheList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
        return pageData;
    }


}








