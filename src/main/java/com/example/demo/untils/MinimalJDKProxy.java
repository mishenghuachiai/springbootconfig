package com.example.demo.untils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MinimalJDKProxy {

    // 1. 定义业务接口
    public interface Hello {
        void sayHello();
    }

    // 2. 真实实现类
    public static class HelloImpl implements Hello {

        @Override
        public void sayHello() {
            System.out.println("Hello from real object!"+10/0);
        }

    }

    // 3. 最简单的 InvocationHandler
    static class SimpleHandler implements InvocationHandler {
        private final Object target;

        public SimpleHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(">>> Proxy before");
            Object result = method.invoke(target, args);  // 调用真实对象
            System.out.println(">>> Proxy after");
            return result;
        }
    }
    // 4. main 方法：创建代理并使用
    public static void main(String[] args) {
        Hello real = new HelloImpl();

        Hello proxy = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class<?>[]{Hello.class},
                new SimpleHandler(real)
        );
        proxy.sayHello();
    }
}
