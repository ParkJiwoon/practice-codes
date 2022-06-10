package com.bcp0109.spring_boot_aop._06_aop_basic_example;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    public String save(String itemId) {
        System.out.println("[orderRepository] 실행");

        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }

        return "ok";
    }
}
