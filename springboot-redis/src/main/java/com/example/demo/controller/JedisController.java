package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

/**
 * @Description: 测试jedis客户端
 * --------------------------------------
 * @ClassName: JedisController.java
 * @Date: 2021/4/4 11:36
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class JedisController {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379, 30000);
        System.out.println(jedis.ping());
        redisLock(jedis);
    }

    /**
     * 测试Redis乐观锁
     */
    public static void redisLock(Jedis jedis) {
        // 选择数据库测试
        jedis.select(1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");

        // 开启事务
        Transaction transaction = jedis.multi();
        try {
            // 通过事务设置key
            transaction.set("demo", jsonObject.toJSONString());
            // 测试事务异常
            // System.out.println(1 / 0);
            transaction.set("demo2", jsonObject.toJSONString());

            // 执行事务
            transaction.exec();
        } catch (Exception e) {
            // 放弃事务
            transaction.discard();
        } finally {
            System.out.println(jedis.get("demo"));
            System.out.println(jedis.get("demo2"));
            jedis.close();
        }
    }

}
