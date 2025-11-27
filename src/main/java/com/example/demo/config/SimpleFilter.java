package com.example.demo.config;




import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@WebFilter(urlPatterns = "/*")  // 拦截所有请求
public class SimpleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("SimpleFilter init");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        System.out.println("SimpleFilter过滤器之前");

        // 放行请求，交给下一个 Filter 或 Controller
        chain.doFilter(request, response);

        System.out.println("SimpleFilter过滤器之后");
    }

    @Override
    public void destroy() {
        System.out.println("SimpleFilter 销毁");
    }
}

