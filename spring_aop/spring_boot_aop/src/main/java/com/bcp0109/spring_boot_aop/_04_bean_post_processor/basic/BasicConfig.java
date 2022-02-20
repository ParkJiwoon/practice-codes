package com.bcp0109.spring_boot_aop._04_bean_post_processor.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfig {

    @Bean(name = "beanA")
    public BasicA a() {
        return new BasicA();
    }
}
