package com.bcp0109.spring_boot_aop._05_aop_basic_example;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        System.out.println("[orderService] 실행");
        orderRepository.save(itemId);
    }
}
