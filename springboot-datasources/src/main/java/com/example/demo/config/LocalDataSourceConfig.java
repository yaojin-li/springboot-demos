package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Description: local 数据源配置
 * --------------------------------------
 * @ClassName: LocalDataSourceConfig.java
 * @Date: 2020/11/17 20:28
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Configuration
public class LocalDataSourceConfig {

    @Autowired
    private Environment environment;

    /**
     * 其他数据源 local数据源
     * */
    @Bean(name = "localDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.local")
    public DataSource localDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("localSqlSessionFactory")
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("localDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(environment.getRequiredProperty("mybatis.local-mapper-locations")));
        return bean.getObject();
    }

    @Bean("localSqlSessionTemplate")
    public SqlSessionTemplate localSqlSessionTemplate(@Qualifier("localSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
