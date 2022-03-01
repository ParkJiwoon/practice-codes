package com.bcp0109.spring_boot_aop._05_aop_basic_example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@SpringBootTest
@DisplayName("스프링 AOP 에서 Aspect 에 순서 적용")
public class Aop5Test {

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
    @DisplayName("Aspect 순서가 적용되어 있어서 트랜잭션 어드바이스가 먼저 수행됨")
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
