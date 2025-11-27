package com.example.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Component
public class SimpleInterceptor implements HandlerInterceptor {

    // Controller 方法执行前
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("拦截器 Before Controller: " + request.getRequestURI());
        return true; // 继续执行，返回 false 会阻止请求
    }

    // Controller 方法执行后，视图渲染前
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器After Controller, before View");
    }

    // 请求完全结束后（包括渲染视图）
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        System.out.println("拦截器After request completion");
    }
}

