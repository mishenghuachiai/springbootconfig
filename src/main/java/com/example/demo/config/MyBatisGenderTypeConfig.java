package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisGenderTypeConfig {
    @Bean
    public GenderTypeHandler genderTypeHandler() {
        return new GenderTypeHandler();
    }
}
