package com.example.demo.springextendspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        System.out.println("⑨ 获得 ApplicationContext：" + ctx.getClass().getName());
    }
}
