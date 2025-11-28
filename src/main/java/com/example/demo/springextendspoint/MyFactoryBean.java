package com.example.demo.springextendspoint;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<String> {

    @Override
    public String getObject() {
        return "hello-from-factorybean";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}

