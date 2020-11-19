package com.example.demo.config;

import com.example.demo.enums.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: dynamic数据源配置
 * --------------------------------------
 * @ClassName: DynamicDataSourceConfig.java
 * @Date: 2020/11/18 19:04
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class DynamicDataSourceConfig {

    @Autowired
    private Environment environment;

    /**
     * dynamic数据源
     * 将所有数据源加入到目标数据源中，并设置默认数据源
     * 将此数据源设置为主数据源，防止切换后读错数据源
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("localDataSource") DataSource localDataSource,
                                        @Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>(8);
        targetDataSource.put(DataSourceEnum.MASTER, masterDataSource);
        targetDataSource.put(DataSourceEnum.LOCAL, localDataSource);
        targetDataSource.put(DataSourceEnum.SLAVE, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        return dataSource;
    }

    @Primary
    @Bean("dynamicSqlSessionFactory")
    public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier("localDataSource") DataSource localDataSource,
                                                      @Qualifier("masterDataSource") DataSource masterDataSource,
                                                      @Qualifier("slaveDataSource") DataSource slaveDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource(localDataSource, masterDataSource, slaveDataSource));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(environment.getRequiredProperty("mybatis.mapper-locations")));
        return bean.getObject();
    }

    @Bean("dynamicSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dynamicSqlSessionTemplate(@Qualifier("dynamicSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
