package com.example.demo.config;

import com.example.demo.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoginFilterConfig {

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new LoginCheckFilter());
        bean.setOrder(1);
        bean.addUrlPatterns("/api/v1/*");
        return bean;
    }
}
