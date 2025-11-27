package com.example.demo.interceptor;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

// 指定拦截 Executor 的 update 和 query 方法
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MySimpleInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 在执行 SQL 之前打印信息
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        System.out.println("Executing SQL ID: " + ms.getId());

        // 执行目标方法
        Object result = invocation.proceed();

        // SQL 执行完成后打印信息
        System.out.println("SQL executed successfully, result: " + result);
        return result;
    }

    @Override
    public Object plugin(Object target) {
        // 使用 Plugin.wrap 包装目标对象
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以从配置文件读取属性（这里不使用）
    }
}
