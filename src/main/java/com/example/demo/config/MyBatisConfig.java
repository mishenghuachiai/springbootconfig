package com.example.demo.config;

import com.example.demo.interceptor.MySimpleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public MySimpleInterceptor mySimpleInterceptor() {
        return new MySimpleInterceptor();
    }
}

