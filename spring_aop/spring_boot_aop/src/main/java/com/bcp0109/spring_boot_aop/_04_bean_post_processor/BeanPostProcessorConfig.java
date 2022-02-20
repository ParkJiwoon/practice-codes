package com.bcp0109.spring_boot_aop._04_bean_post_processor;

import com.bcp0109.spring_boot_aop._04_bean_post_processor.basic.BasicA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPostProcessorConfig {

    @Bean(name = "beanA")
    public BasicA basicA() {
        return new BasicA();
    }

    @Bean
    public AToBPostProcessor helloPostProcessor() {
        return new AToBPostProcessor();
    }
}
