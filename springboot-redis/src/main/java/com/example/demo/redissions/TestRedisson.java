package com.example.demo.redissions;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 可重入锁
 * --------------------------------------
 * @ClassName: TestRedisson.java
 * @Date: 2021/12/6 0006  17:26
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TestRedisson {
    @Autowired
    RedissonClient redissonClient;

    public String justTestRedisson() {
        // 获取分布式锁。锁名相同即为同一把锁。
        RLock lock = redissonClient.getLock("lock_name");

        // 加锁（阻塞等待），默认过期时间是30秒
        lock.lock();
        try {
            // 业务过长，redisson会自动给锁续期。
            Thread.sleep(1000);
            //
            System.out.println("加锁成功，执行业务逻辑....");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁。业务执行完成不会继续续期，即使没有手动释放，30s后自动释放锁。
            lock.unlock();
        }

        return "Hello Redisson!";
    }

}
