package com.example.demo.springextendspoint;

import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MySmartInstantiationAwareBeanPostProcessor
        implements SmartInstantiationAwareBeanPostProcessor {

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) {
        if (beanName.equals("employee")) {
            System.out.println("⑥ 预测类型：" + beanClass.getName());
        }
        return beanClass;
    }
}

