package com.cssl.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
public class filterConfig {
    @Bean
    public FilterRegistrationBean<loginFilter> loginFilterBean(){
        FilterRegistrationBean<loginFilter> loginFilterFilter=new FilterRegistrationBean<>();
        loginFilterFilter.setFilter(new loginFilter());
        loginFilterFilter.addUrlPatterns("/user/login/*");
        System.out.println("filterConfig过滤器配置启动______________");
        return loginFilterFilter;

    }
}
