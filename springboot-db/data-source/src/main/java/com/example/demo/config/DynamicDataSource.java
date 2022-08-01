package com.example.demo.config;

import com.example.demo.enums.DataSourceEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description: 通过线程动态获取数据源上下文，用于使用注解、aop切换数据源
 * --------------------------------------
 * @ClassName: DynamicDataSource.java
 * @Date: 2020/11/19 12:25
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DataSourceEnum>  DATA_SOURCE = new ThreadLocal<>();

    @Override
    protected DataSourceEnum determineCurrentLookupKey() {
        return DATA_SOURCE.get();
    }

    /**
     * 切换数据源
     * */
    public static void setDataSource(DataSourceEnum dataSource){
        DATA_SOURCE.set(dataSource);
    }

    /**
     * 获取数据源
     * */
    public static DataSourceEnum getDataSource(){
        return DATA_SOURCE.get();
    }

    public static void removeDataSource(){
        DATA_SOURCE.remove();
    }

}
