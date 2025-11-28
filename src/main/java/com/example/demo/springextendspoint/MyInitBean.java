package com.example.demo.springextendspoint;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MyInitBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("⑦ InitializingBean 初始化完成");
    }
}

