package demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("traceId")
    public void test() {
        LOGGER.info("test");
    }

    @RequestMapping("sync")
    public void sync() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                LOGGER.info("sync..{}", Thread.currentThread().getId());
            }, String.valueOf(i)).start();
        }
    }


}
