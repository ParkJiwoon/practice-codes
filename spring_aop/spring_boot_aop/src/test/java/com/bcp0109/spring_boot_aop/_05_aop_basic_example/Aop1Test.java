package com.bcp0109.spring_boot_aop._05_aop_basic_example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Import(AspectV1.class)
@SpringBootTest
@DisplayName("스프링 AOP 를 적용해서 로그 추가")
public class Aop1Test {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("프록시 적용된 상태")
    void aopInfo() {
        System.out.println("isAopProxy, orderService=" + AopUtils.isAopProxy(orderService));
        System.out.println("isAopProxy, orderRepository=" + AopUtils.isAopProxy(orderRepository));

        Assertions.assertTrue(AopUtils.isAopProxy(orderService));
        Assertions.assertTrue(AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> orderService.orderItem("ex"))
                .withMessage("예외 발생!");
    }
}
