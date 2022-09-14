package demo.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import demo.ThreadPoolTask.TtlThreadPoolTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.concurrent.*;

/**
 * @Description: --------------------------------------
 * @ClassName: TraceIdController.java
 * @Date: 2022/5/3 0003  18:06
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class TraceIdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdController.class);

    /**
     * 单线程链路追踪
     * 拦截器 或 过滤器
     */
    @RequestMapping("traceId")
    public void test() {
        LOGGER.info("test");
    }

    /**
     * 多线程异步链路追踪
     * TtlMdcAdapter + 拦截器 或 TtlMdcAdapter + 过滤器
     */
    @RequestMapping("sync")
    public void sync() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                LOGGER.info("sync..{}", Thread.currentThread().getId());
            }, String.valueOf(i)).start();
        }
    }


    @RequestMapping("executor")
    public void executor() {
        TtlThreadPoolTaskExecutor paymentThreadPool = new TtlThreadPoolTaskExecutor();
        paymentThreadPool.setCorePoolSize(5);
        paymentThreadPool.setMaxPoolSize(10);
        paymentThreadPool.setKeepAliveSeconds(60);
        paymentThreadPool.setQueueCapacity(1000);
        paymentThreadPool.setThreadFactory(new ThreadFactoryBuilder().build());
        paymentThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        paymentThreadPool.initialize();

        for (int i = 1; i <= 30; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            paymentThreadPool.execute(task);
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            LOGGER.info(String.format("线程[%s]-任务[%s] 执行中...",
                    Thread.currentThread().getName(), name));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
