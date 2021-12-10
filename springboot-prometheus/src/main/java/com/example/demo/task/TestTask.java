package com.example.demo.task;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: --------------------------------------
 * @ClassName: TestTask.java
 * @Date: 2021/12/9 0009  16:03
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class TestTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTask.class);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            2,
            1000,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    private final Counter targetCounter;
    private final Counter timeCounter;

    // 仪表盘数据--模拟任务个数
    private Integer toDoTaskCount = 0;
    // 仪表盘数据--模拟任务总数
    private Integer totalTaskCount = 1000;

    public TestTask(MeterRegistry meterRegistry) {
        // prometheus 计数器数据展示
        this.targetCounter = Counter.builder("TARGET_COUNTER").tag("result", "failed")
                .description("Number of failed request target").register(meterRegistry);
        this.timeCounter = Counter.builder("TIME_COUNTER").tag("result", "failed")
                .description("Number of failed request time").register(meterRegistry);

        // prometheus 仪表盘数据展示
        Gauge.builder("TO_DO_TASK_COUNT", this, TestTask::getToDoTaskCount).tag("type", "toDoTaskNum")
                .description("to_do_task_count for test").register(meterRegistry);
        Gauge.builder("TOTAL_TASK_COUNT", this, TestTask::getTotalTaskCount).tag("type", "toDoTaskNum")
                .description("total_task_count for test").register(meterRegistry);
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void invokeTask() {
        // 创建线程池模拟执行
        TestJob job = new TestJob(targetCounter, timeCounter);
        executor.execute(job);
        LOGGER.info("【线程池】执行完成。。。");
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void inCount() {
        // 模拟仪表盘数据自增
        toDoTaskCount++;
        totalTaskCount++;
        LOGGER.info("【仪表盘】toDoTaskCount、totalTaskCount 自增完成。。。");
    }

    public Integer getToDoTaskCount() {
        LOGGER.info(String.format("模拟任务 个数[%s] - [%s]", toDoTaskCount, System.currentTimeMillis()));
        return toDoTaskCount;
    }

    public Integer getTotalTaskCount() {
        LOGGER.info(String.format("模拟任务 总数[%s] - [%s]", totalTaskCount, System.currentTimeMillis()));
        return totalTaskCount;
    }


}
