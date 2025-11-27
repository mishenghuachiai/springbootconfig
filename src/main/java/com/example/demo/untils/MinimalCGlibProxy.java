package com.example.demo.untils;



import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MinimalCGlibProxy {

    // 1. 定义业务接口
    public interface Hello {
        void sayHello();
    }

    // 2. 真实实现类
    public static class HelloImpl implements Hello {
        @Override
        public void sayHello() {
            System.out.println("Hello from real object!" + 10/0);
        }
    }

    public static void main(String[] args) {

        // 3. 创建代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloImpl.class); // 代理目标类，而不是接口！
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {

                System.out.println("Before method...");

                Object result;
                try {
                    result = proxy.invokeSuper(obj, args); // 调用目标方法
                } catch (Exception e) {
                    System.out.println("Exception caught: " + e.getMessage());
                    throw e;
                }

                System.out.println("After method...");
                return result;
            }
        });

        // 4. 获取代理对象
        Hello proxy = (Hello) enhancer.create();

        // 5. 调用代理方法
        proxy.sayHello();
    }
}
