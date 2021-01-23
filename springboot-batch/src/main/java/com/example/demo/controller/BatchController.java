package com.example.demo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: --------------------------------------
 * @ClassName: BatchController.java
 * @Date: 2021/1/23 17:21
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@RestController
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;

    /**
     * 指定对应的多个job
     */
    @Autowired
    Job txtJob;

    @Autowired
    Job csvJob;

    @GetMapping("/csvBatchTest")
    public void csvBatchTest() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(csvJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/txtBatchTest")
    public void txtBatchTest() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(txtJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
