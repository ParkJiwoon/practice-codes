package com.bcp0109.spring_boot_aop._07_internal_call;

import org.springframework.stereotype.Component;

@Component
public class CallServiceV0 {

    public void external() {
        System.out.println("call external");
        internal(); // 내부 메서드 호출 (this.internal())
    }

    public void internal() {
        System.out.println("call internal");
    }
}
