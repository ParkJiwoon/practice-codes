package com.bcp0109.spring_boot_aop._06_aop_basic_example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Import(AspectV4Pointcut.class)
@SpringBootTest
@DisplayName("스프링 AOP 에서 포인트컷을 별도로 선언한 후 Aspect 에 적용")
public class Aop4Test {

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
    @DisplayName("OrderService 는 로그, 트랜잭션 둘다 적용하고 OrderRepository 는 로그만 적용")
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
