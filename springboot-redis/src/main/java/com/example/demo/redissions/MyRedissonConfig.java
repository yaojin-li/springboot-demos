package com.example.demo.redissions;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Configuration;
import org.redisson.api.RedissonClient;

/**
 * @Description: 通过redisson创建分布式锁的配置类
 * --------------------------------------
 * @ClassName: MyRedissonConfig.java
 * @Date: 2021/12/6 0006  17:00
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class MyRedissonConfig {

    RedissonClient redisson() throws Exception {
        // 创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("localhost:6379");
        // 设置看门狗检查锁的超时时间，默认30秒
//        config.setLockWatchdogTimeout(30 * 1000);
        return Redisson.create(config);
    }

}
