package com.bcp0109.spring_boot_aop._02_internal_call;

import org.springframework.stereotype.Component;

@Component
public class InternalService {

    public void internal() {
        System.out.println("call internal");
    }
}
