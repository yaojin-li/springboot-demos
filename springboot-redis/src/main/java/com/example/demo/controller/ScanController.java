package com.example.demo.controller;

import jodd.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.*;
import redis.clients.jedis.util.JedisClusterCRC16;

import java.util.*;

/**
 * @Description: 测试redis scan批量删除
 * --------------------------------------
 * @ClassName: ScanController.java
 * @Date: 2021/12/22 0022  15:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class ScanController {
    private static final Logger logger = LoggerFactory.getLogger(ScanController.class);

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    Jedis jedis;

    @RequestMapping("/scan")
    public List<String> scan() {
        return getClusterKeys("*Single*", 10000);
    }

    /*** redis集群模式 scan操作 **/
    public List<String> getClusterKeys(String matchKey, Integer count) {
        List<String> list = new ArrayList<>();
        RedisClusterConnection redisClusterConnection = redisTemplate.getConnectionFactory().getClusterConnection();
        //这里是获取edispool的另外一种方式与上边的pipline可以对比下，两种方式都可以实现
        Map<String, JedisPool> clusterNodes = ((JedisCluster) redisClusterConnection.getNativeConnection()).getClusterNodes();
        for (Map.Entry<String, JedisPool> entry : clusterNodes.entrySet()) {
            //获取单个的jedis对象
            Jedis jedis = entry.getValue().getResource();
            // 判断非从节点(因为若主从复制，从节点会跟随主节点的变化而变化)，此处要使用主节点从主节点获取数据
            if (!jedis.info("replication").contains("role:slave")) {
                List<String> keys = getScan(jedis, matchKey, count);
                if (keys.size() > 0) {
                    Map<Integer, List<String>> map = new HashMap<>(8);
                    //接下来的循环不是多余的，需要注意
                    for (String key : keys) {
                        // cluster模式执行多key操作的时候，这些key必须在同一个slot上，不然会报:JedisDataException:
                        int slot = JedisClusterCRC16.getSlot(key);
                        // 按slot将key分组，相同slot的key一起提交
                        if (map.containsKey(slot)) {
                            map.get(slot).add(key);
                        } else {
                            List<String> list1 = new ArrayList();
                            list1.add(key);
                            map.put(slot, list1);
                        }
                    }
                    for (Map.Entry<Integer, List<String>> integerListEntry : map.entrySet()) {
                        list.addAll(integerListEntry.getValue());
                    }
                }
            }
        }
        return list;
    }

    /**
     * 通过redis scan删除keys
     * redis集群模式
     */
    public void delClusterKeys(String matchKey, Integer count) {
        long beginTime = System.currentTimeMillis();
        RedisClusterConnection redisClusterConnection = redisTemplate.getConnectionFactory().getClusterConnection();
        //这里是获取edispool的另外一种方式与上边的pipline可以对比下，两种方式都可以实现
        Map<String, JedisPool> clusterNodes = ((JedisCluster) redisClusterConnection.getNativeConnection()).getClusterNodes();
        for (Map.Entry<String, JedisPool> entry : clusterNodes.entrySet()) {
            //获取单个的jedis对象
            Jedis jedis = entry.getValue().getResource();
            if (!jedis.info("replication").contains("role:slave")) {
                delScan(jedis, matchKey, count);
            }
        }
        logger.info("耗时：" + (System.currentTimeMillis() - beginTime));
    }


    /*** redis单节点 scan操作 **/
    public List<String> cleanSingleKeys(String matchKey) {
        return getScan(jedis, matchKey, 100);
    }

    /**
     * scan 批量扫描模糊匹配 key
     *
     * @param redisService redis连接
     * @param key          模糊查询key
     * @param count        scan扫描返回记录行数
     */
    public List<String> getScan(Jedis redisService, String key, Integer count) {
        List<String> keyResult = new ArrayList<>();
        ScanParams params = new ScanParams();
        params.match(key);
        params.count(count);
        String cursor = "0";

        while (true) {
            ScanResult scanResult = redisService.scan(cursor, params);
            List<String> elements = scanResult.getResult();
            if (!CollectionUtils.isEmpty(elements)) {
                keyResult.addAll(elements);
            }
            cursor = scanResult.getCursor();
            if (ScanParams.SCAN_POINTER_START.equals(cursor)) {
                break;
            }
        }
        return keyResult;
    }


    /**
     * 通过redis scan 批量扫描删除 key
     *
     * @param redisService redis连接
     * @param key          模糊查询key
     * @param count        scan扫描返回记录行数
     */
    public void delScan(Jedis redisService, String key, Integer count) {
        ScanParams params = new ScanParams();
        params.match(key);
        params.count(count);
        String cursor = "0";

        while (true) {
            ScanResult scanResult = redisService.scan(cursor, params);
            List<String> elements = scanResult.getResult();
            if (!CollectionUtils.isEmpty(elements)) {
                for (String element : elements) {
                    redisService.del(element);
                }
                // 对于大规模删除可注释log日志打印
                logger.info(String.format("删除[%s]条缓存", elements.size()));
            }
            cursor = scanResult.getCursor();
            if (ScanParams.SCAN_POINTER_START.equals(cursor)) {
                break;
            }
        }
    }


}
