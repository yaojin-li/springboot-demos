package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Date;

/**
 * @Description: --------------------------------------
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

    public static void main(String[] args) {
        // 连接池
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            TimeLag t = new TimeLag();
            Pipeline pipelined = jedis.pipelined();

            // 插入多条数据
            for (Integer i = 0; i < 100000; i++) {
                pipelined.set(i.toString(), i.toString());
            }

//            // 删除多条数据
//            for (Integer i = 0; i < 100000; i++) {
//                pipelined.del(i.toString());
//            }

            pipelined.sync();
            System.out.println(t.cost());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}


class TimeLag {

    private Date start;
    private Date end;

    public TimeLag() {
        start = new Date();
    }

    public String cost() {
        end = new Date();
        long c = end.getTime() - start.getTime();
        return new StringBuffer().append("cost ").append(c).append(" milliseconds (").append(c / 1000).append(" seconds).").toString();
    }

}
