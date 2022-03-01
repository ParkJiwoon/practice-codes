package com.bcp0109.spring_boot_aop._04_bean_post_processor;

import com.bcp0109.spring_boot_aop._04_bean_post_processor.basic.BasicA;
import com.bcp0109.spring_boot_aop._04_bean_post_processor.basic.BasicB;
import com.bcp0109.spring_boot_aop._04_bean_post_processor.basic.BasicConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

class BasicTest {

    @Test
    @DisplayName("기본 Bean 등록 테스트")
    void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        // Bean A 는 등록되어 있음
        BasicA basicA = applicationContext.getBean(BasicA.class);
        basicA.helloA();

        // beaA 라는 이름으로도 가져올 수 있음
        BasicA beanBasicA = applicationContext.getBean("basicBeanA", BasicA.class);
        beanBasicA.helloA();

        assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
                .isThrownBy(() -> applicationContext.getBean(BasicB.class));
    }

    @Test
    @DisplayName("빈 프로세서를 활용해서 'beanA' 라는 이름으로 B 객체가 등록됨")
    void postProcessor() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // Bean A 가 등록되어 있지 않음
        assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
                .isThrownBy(() -> applicationContext.getBean(BasicA.class));

        // beanA 라는 이름으로 B 객체가 등록되어 있음
        BasicB beanB = applicationContext.getBean("beanA", BasicB.class);
        beanB.helloB();
    }
}
