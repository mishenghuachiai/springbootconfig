package com.example.demo.aop;


import com.example.demo.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(logAnnotation)") // 拦截标注了 @Log 的方法
    public Object around(ProceedingJoinPoint joinPoint, Log logAnnotation) throws Throwable {
        System.out.println("=== 日志切面Log Start ===");
        System.out.println("日志切面 Method: " + joinPoint.getSignature());
        System.out.println("日志切面 Description: " + logAnnotation.value());

        Object result;
        try {
            result = joinPoint.proceed(); // 执行目标方法
        } catch (Exception e) {
            System.out.println("日志切面 Exception: " + e.getMessage());
            throw e;
        }

        System.out.println("=== 日志切面 Log End ===");
        return result;
    }
}

