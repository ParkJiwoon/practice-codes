package com.jiwoon.practicewebmvc.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A: A 사용자가 1번 서비스에 10000 원 주문
        statefulService1.order("userA", 10000);

        // Thread B : B 사용자 2번 서비스에 20000 원 주문
        statefulService2.order("userB", 20000);

        // Thread A : A 사용자가 1번 서비스에 주문금액 조회 했는데 20000 원이 뜸 !!
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(price).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
