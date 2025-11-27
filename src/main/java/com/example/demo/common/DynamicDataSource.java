package com.example.demo.common;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 返回当前线程所使用的数据源标识
        return DataSourceContextHolder.getDataSource();
    }
}
