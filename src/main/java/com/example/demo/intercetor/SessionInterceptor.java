package com.example.demo.intercetor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SessionInterceptor implements WebMvcConfigurer {

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        List list = new ArrayList();
//        list.add("/toIndex");
//        list.add("/login");
//        list.add("/toRegister");
//        list.add("/register");
//        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/*").excludePathPatterns(list);
    }
}
