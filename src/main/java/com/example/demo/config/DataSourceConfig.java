package com.example.demo.config;

import com.example.demo.common.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class DataSourceConfig {

    @Bean("ds1")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource ds1() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }

    // 读取 ds2 配置
    @Bean("ds2")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource ds2() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }


    // 动态数据源

    @Bean
    @Primary
    public DataSource dynamicDataSource(DataSource ds1, DataSource ds2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("ds1", ds1);
        targetDataSources.put("ds2", ds2);

        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(ds1);
        return dynamicDataSource;
    }
}
