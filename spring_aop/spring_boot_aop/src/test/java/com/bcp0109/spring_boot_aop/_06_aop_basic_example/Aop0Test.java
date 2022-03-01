package com.bcp0109.spring_boot_aop._06_aop_basic_example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DisplayName("스프링 AOP 가 적용되지 않은 기본 테스트")
public class Aop0Test {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("프록시 적용되어 있지 않은 상태")
    void aopInfo() {
        System.out.println("isAopProxy, orderService=" + AopUtils.isAopProxy(orderService));
        System.out.println("isAopProxy, orderRepository=" + AopUtils.isAopProxy(orderRepository));

        Assertions.assertFalse(AopUtils.isAopProxy(orderService));
        Assertions.assertFalse(AopUtils.isAopProxy(orderRepository));
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
