package com.example.demo.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 注解在方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时可通过反射读取
@Documented
public @interface Log {
    String value() default ""; // 可选描述
}

