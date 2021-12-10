package com.example.demo.task;

import io.micrometer.core.instrument.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: --------------------------------------
 * @ClassName: TestJob.java
 * @Date: 2021/12/9 0009  16:47
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TestJob implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestJob.class);

    // 计数器1
    private final Counter targetCounter;
    // 计数器2
    private final Counter timeCounter;

    public TestJob(Counter targetCounter, Counter timeCounter) {
        this.targetCounter = targetCounter;
        this.timeCounter = timeCounter;
    }

    @Override
    public void run() {
        targetCounter.increment();
        timeCounter.increment();
        LOGGER.info("【计数器】targetCounter、timeCounter自增完成。。。");
    }
}
