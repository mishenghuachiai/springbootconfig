package com.example.demo.springextendspoint;

import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor
        implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        if (beanName.equals("employeeMapper")) {
            System.out.println("⑤ 实例化前：" + beanName);
        }
        return null; // 返回 null 表示不接管实例化
    }
}

