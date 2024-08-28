package com.luobd.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

    public ConfigurationBean() {
        System.out.println("ConfigurationBean init");
    }
    @Bean
    public BeanOne beanOne() {
        return new BeanOne();
    }
}
