package com.example.demo.elect;

import com.example.demo.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Redis分布式锁实现选举机制
 * 缺点：单点故障时，需要等到过期时间后，其余节点再选举主节点
 * --------------------------------------
 * @ClassName: RedisElectLeader.java
 * @Date: 2021/12/6 0006  9:43
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class RedisElectLeader implements ElectLeaderService {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisElectLeader.class);

    /**
     * 选举节点
     */
    private final String electNodeName = IPUtil.getLocalHostIdentifier() + "_" + UUID.randomUUID().toString().replace("_", "");

    /**
     * Redis中存储leader的key
     */
    private final static String LEADER_KEY = "task-name:leader";

    /**
     * 是否是主节点
     */
    private volatile boolean leader = false;

    /**
     * 是否是选举状态
     */
    private volatile boolean electing = false;

    /**
     * 异步提交选举任务
     */
    private ExecutorService executorService = new ThreadPoolExecutor(
            4,
            10,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>()
    );

    /**
     * 缓存key过期时间
     */
    private static final int REDIS_KEY_TIME_OUT = 60;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public synchronized boolean startElect() {
        // 当前节点已开起选举，则推出
        if (electing) {
            LOGGER.warn(String.format("electName[%s]已经开起选举，本次选举无效", electNodeName));
            return false;
        }
        // 选举前初始化
        init();
        return true;
    }

    private void init() {
        LOGGER.info(String.format("节点[%s]选举主节点前初始化...", electNodeName));
        // 当前选举状态更新
        electing = true;
        // 通过异步线程提交选举任务
        executorService.submit(new ElectLeaderTask());
        executorService.shutdown();
    }

    // 选举任务
    private class ElectLeaderTask implements Runnable {
        @Override
        public void run() {
            LOGGER.info(String.format("节点[%s]开起选举任务...", electNodeName));
            // 默认情况下选举状态一直为true，当主节点宕机后其他节点竞争获取Redis分布式锁
            while (electing) {
                try {
                    elect();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                try {
                    // 间隔55秒后重新尝试选举
                    TimeUnit.SECONDS.sleep(REDIS_KEY_TIME_OUT - 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info(String.format("节点[%s]退出选举任务...", electNodeName));
        }

        private void elect() {
            boolean locked = redisTemplate.opsForValue().setIfAbsent(LEADER_KEY, electNodeName, REDIS_KEY_TIME_OUT, TimeUnit.SECONDS);
            // 获取到锁，当前节点设置为主节点
            if (locked) {
                leader = true;
                LOGGER.info(String.format("============节点[%s]为主节点===========", electNodeName));
                // TODO 分布式系统针对主节点执行调度任务
                // TODO 执行业务处理...

            } else {
                // 未能获取到锁，判断获取到锁的主节点
                String value = (String) redisTemplate.opsForValue().get(LEADER_KEY);
                // 主节点不出意外一直固定是一个节点（每次会刷新Redis key的过期时间）
                if (electNodeName.equals(value)) {
                    LOGGER.info(String.format("主节点[%s] keep alive...", electNodeName));
                    // 刷新当前主节点过期时间
                    redisTemplate.expire(LEADER_KEY, REDIS_KEY_TIME_OUT, TimeUnit.SECONDS);
                }
                LOGGER.info(String.format("当前节点为[%s]，主节点为[%s]", electNodeName, value));
            }
        }

    }

    @Override
    public boolean isLeader() {
        return leader;
    }

    @Override
    public boolean stopElect() {
        // 如果状态不是选举状态
        if (!electing) {
            LOGGER.warn(String.format("electName[%s] 退出选举任务", electNodeName));
            return false;
        }
        electing = false;
        leader = false;
        return true;
    }

    @Override
    public String getElectNodeName() {
        return electNodeName;
    }
}
