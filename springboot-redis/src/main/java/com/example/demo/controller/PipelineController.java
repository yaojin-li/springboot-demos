package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.*;
import redis.clients.jedis.util.JedisClusterCRC16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 测试redis pipeline
 * --------------------------------------
 * @ClassName: PipelineController.java
 * @Date: 2021/6/4 17:17
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
@SuppressWarnings("all")
public class PipelineController {
    @Autowired
    private RedisTemplate redisTemplate;

//    @Scheduled(cron = "0 */1 * * * ?")
    public void test() {
        List<String> keys = new ArrayList<>();
        keys.add("Single1");
        keys.add("Single2");
        keys.add("Single3");
        System.out.println(JSONObject.toJSONString(multiGet(keys)));
        System.out.println(JSONObject.toJSONString(hgetpipeline(keys)));
    }

    /**
     * 批量查询
     * 只建立一次连接批量查询，减少IO
     */
    public List<String> multiGet(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 批量查询
     * 只建立一次连接批量查询，减少IO
     */
    public List<Object> hgetpipeline(List<String> keys) {
        List<Object> results = redisTemplate.executePipelined(
                new RedisCallback<Object>() {
                    @Override
                    public Object doInRedis(RedisConnection connection) throws DataAccessException {
                        StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                        for (String key : keys) {
                            stringRedisConn.get(key);
                        }
                        return null;
                    }
                });
        return results;
    }


    public List<String> clusterPiplineGet(List<String> keys) {
        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        RedisSerializer<String> valueSerializer = redisTemplate.getValueSerializer();
        // 记录节点与key之间的对应关系
        HashMap<RedisClusterNode, List<String>> nodeKeyMap = new HashMap<>();
        List<String> result = new ArrayList<>();
        RedisClusterConnection redisClusterConnection = redisTemplate.getConnectionFactory().getClusterConnection();
        try {
            //通过计算每个key的槽点，并获取相应节点
            Iterable<RedisClusterNode> redisClusterNodes = redisClusterConnection.clusterGetNodes();
            for (RedisClusterNode redisClusterNode : redisClusterNodes) {
                RedisClusterNode.SlotRange slotRange = redisClusterNode.getSlotRange();
                for (String key : keys) {
                    int slot = JedisClusterCRC16.getSlot(key);
                    if (slotRange.contains(slot)) {
                        List<String> list = nodeKeyMap.get(redisClusterNode);
                        if (null == list) {
                            list = new ArrayList<>();
                            nodeKeyMap.putIfAbsent(redisClusterNode, list);
                        }
                        list.add(key);
                    }
                }
            }
            for (Map.Entry<RedisClusterNode, List<String>> clusterNodeListEntry : nodeKeyMap.entrySet()) {
                RedisClusterNode redisClusterNode = clusterNodeListEntry.getKey();
                //连接节点
                JedisPool jedisPool = ((JedisCluster) redisClusterConnection.getNativeConnection()).getClusterNodes().get(new HostAndPort(redisClusterNode.getHost(), redisClusterNode.getPort()).toString());
                List<String> nodeListEntryValue = clusterNodeListEntry.getValue();

                byte[][] arr = new byte[nodeListEntryValue.size()][];
                int count = 0;
                //获取key数据
                for (String nodeKey : nodeListEntryValue) {
                    arr[count++] = keySerializer.serialize(nodeKey);
                }
                Jedis jedis = jedisPool.getResource();
                List<Response<byte[]>> responses = new ArrayList<>();
                try {
                    Pipeline pipeline = jedis.pipelined();
                    for (String nodeKey : nodeListEntryValue) {
                        responses.add(pipeline.get(keySerializer.serialize(nodeKey)));
                    }
                    pipeline.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jedis.close();
                }
                for (Response<byte[]> response : responses) {
                    byte[] data = response.get();
                    result.add(valueSerializer.deserialize(data));
                }
            }
        } finally {
            RedisConnectionUtils.releaseConnection(redisClusterConnection, redisTemplate.getConnectionFactory());
        }
        return result;
    }


}
