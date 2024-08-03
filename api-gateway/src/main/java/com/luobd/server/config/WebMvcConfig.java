package com.luobd.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Resource
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns=new ArrayList<>();
        patterns.add("/api/auth/login");
        patterns.add("/doc.html");
        patterns.add("/swagger-resources/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        //注册拦截器类，添加黑名单(addPathPatterns("/**")),‘/*’只拦截一个层级，'/**'拦截全部
        // 和白名单(excludePathPatterns("List类型参数"))，将不必拦截的路径添加到List列表中
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }


}
