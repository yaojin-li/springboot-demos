package com.example.demo.config;

import com.example.demo.items.processor.TxtItemProcessor;
import com.example.demo.items.reader.TxtItemReader;
import com.example.demo.items.writer.TxtItemWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: 读取txt的跑批任务配置
 * --------------------------------------
 * @ClassName: TxtBatchJobConfig.java
 * @Date: 2021/1/12 19:49
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
@EnableBatchProcessing
public class TxtBatchJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    /**
     * 1. 配置一个ItemReader，即数据的读取逻辑
     */
    @Bean
    public ItemReader<String> txtItemReader() {
        return new TxtItemReader().read();
    }

    /**
     * 2. 配置一个ItemProcessor，即数据的处理逻辑
     */
    @Bean
    public ItemProcessor txtItemProcessor() {
        return new TxtItemProcessor();
    }

    /**
     * 3. 配置一个ItemWriter，即数据的写入逻辑
     */
    @Bean
    public ItemWriter<String> txtItemWriter() {
        return new TxtItemWriter();
    }

//    @Override
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    /**
     * 配置step
     */
    @Bean
    public Step txtStep() {
        // Step通过stepBuilderFactory进行配置
        //通过get获取一个StepBuilder，参数数Step的name
        return stepBuilderFactory.get("txtStep")
                //方法的参数2，表示每读取到两条数据就执行一次write操作
                .<String, String>chunk(2)
                .reader(txtItemReader())
                .writer(txtItemWriter())
                .processor(txtItemProcessor())
                .build();
    }

    /**
     * 配置job
     */
    @Bean
    public Job txtJob() {
        return jobBuilderFactory.get("txtJob")
                .listener(new JobExecutionListenerSupport() {
                    //所有处理结束后调用
                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                            System.out.println("txt job process ok ...");
                        }
                    }
                })
                .flow(txtStep())
                .end()
                .build();
    }


}

