package com.bcp0109.spring_boot_aop._04_bean_post_processor;

import com.bcp0109.spring_boot_aop._04_bean_post_processor.basic.BasicA;
import com.bcp0109.spring_boot_aop._04_bean_post_processor.basic.BasicB;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AToBPostProcessor implements BeanPostProcessor {

    /**
     * A 객체를 B 객체로 바꿔치기 하는 빈 후처리기
     * A 객체가 아니면 원래 객체를 반환
     *
     * @param bean      빈 객체
     * @param beanName  빈 이름
     * @return          반환되는 빈 객체
     */

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanName=" + beanName + ", bean=" + bean);
        if (bean instanceof BasicA) {
            return new BasicB();
        }
        return bean;
    }
}
