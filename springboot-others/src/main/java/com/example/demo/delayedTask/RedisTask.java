//package com.example.demo.delayedTask;
//
//import org.springframework.util.CollectionUtils;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.Tuple;
//
//import java.util.Calendar;
//import java.util.Set;
//
///**
// * @Description: 通过redis实现延迟任务
// * 待改进：多线程并发下重复消费
// * --------------------------------------
// * @ClassName: RedisTask.java
// * @Date: 2021/9/30 10:30
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//public class RedisTask {
//    private static final String ADDR = "localhost";
//    private static final int PORT = 6379;
//    private static JedisPool jedisPool = new JedisPool(ADDR, PORT);
//
//    public static Jedis getJedis() {
//        return jedisPool.getResource();
//    }
//
//    public static void main(String[] args) {
//        RedisTask redisTask = new RedisTask();
//        redisTask.productionDelayMessage();
//        redisTask.consumerDelayMessage();
//    }
//
//    /**
//     * 生产者
//     */
//    public void productionDelayMessage() {
//        for (int i = 0; i < 5; i++) {
//            Calendar instance = Calendar.getInstance();
//            instance.add(Calendar.SECOND, 3);
//            int second3later = (int) (instance.getTimeInMillis() / 1000);
//            RedisTask.getJedis().zadd("OrderId", second3later, "OID0001" + i);
//            System.out.println(System.currentTimeMillis() +
//                    " redis生成订单任务：[OrderId]" + "OID0001" + i);
//        }
//    }
//
//    /**
//     * 消费者
//     */
//    public void consumerDelayMessage() {
//        Jedis jedis = RedisTask.getJedis();
//
//        while (true) {
//            // 获取redis中的任务
//            Set<Tuple> items = jedis.zrangeWithScores("OrderId", 0, 1);
//
//            if (CollectionUtils.isEmpty(items)) {
//                System.out.println("当前没有等待的任务");
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                continue;
//            }
//
//            int score = (int) ((Tuple) items.toArray()[0]).getScore();
//            Calendar calendar = Calendar.getInstance();
//            int nowSecond = (int) (calendar.getTimeInMillis() / 1000);
//            // 多线程并发情况下出现重复消费问题
////            if (nowSecond >= score) {
////                // 获取订单号
////                String orderId = ((Tuple) items.toArray()[0]).getElement();
////                // 模拟消费（删除）
////                jedis.zrem("OrderId", orderId);
////                System.out.println(System.currentTimeMillis() +
////                        " redis消费一个任务：[OrderId]" + orderId);
////            }
//
//            //
//            if (nowSecond >= score) {
//                // 获取订单号
//                String orderId = ((Tuple) items.toArray()[0]).getElement();
//                // 模拟消费（删除）
//                Long num = jedis.zrem("OrderId", orderId);
//                // 考虑多线程并发情况重复消费，若已经删除则不消费
//                // 或考虑分布式锁实现
//                if (num != null && num > 0) {
//                    System.out.println(System.currentTimeMillis() +
//                            " redis消费一个任务：[OrderId]" + orderId);
//                }
//            }
//
//        }
//    }
//
//
//}
