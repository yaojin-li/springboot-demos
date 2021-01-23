package com.example.demo.config;

import com.example.demo.dto.User;
import com.example.demo.items.processor.CsvItemProcessor;
import com.example.demo.items.reader.CsvItemReader;
import com.example.demo.items.writer.CsvItemWriter;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: 读取csv的跑批任务配置
 * --------------------------------------
 * @ClassName: CsvBatchJobConfig.java
 * @Date: 2021/1/12 19:49
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
@EnableBatchProcessing
public class CsvBatchJobConfig {

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
    public ItemReader<User> csvItemReader() {
        return new CsvItemReader().read();
    }

    /**
     * 2. 配置一个ItemProcessor，即数据的处理逻辑
     */
    @Bean
    public ItemProcessor<User, User> csvItemProcessor() {
        return new CsvItemProcessor();
    }

    /**
     * 3. 配置一个ItemWriter，即数据的写入逻辑
     */
    @Bean
    public ItemWriter<User> csvItemWriter(DataSource dataSource) {
        return new CsvItemWriter().write(dataSource);
    }

    /**
     * 配置step
     */
    @Bean
    public Step csvStep() {
        // Step通过stepBuilderFactory进行配置
        //通过get获取一个StepBuilder，参数数Step的name
        return stepBuilderFactory.get("csvStep")
                //方法的参数2，表示每读取到两条数据就执行一次write操作
                .<User, User>chunk(2)
                .reader(csvItemReader())
                .processor(csvItemProcessor())
                .writer(csvItemWriter(dataSource))
                .build();
    }

    /**
     * 配置job
     */
    @Bean
    Job csvJob() {
        return jobBuilderFactory.get("csvJob")
                .listener(new JobExecutionListenerSupport() {
                    //所有处理结束后调用
                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                            System.out.println("csv job process ok ...");
                        }
                    }
                })
                .flow(csvStep())
                .end()
                .build();
    }

}

