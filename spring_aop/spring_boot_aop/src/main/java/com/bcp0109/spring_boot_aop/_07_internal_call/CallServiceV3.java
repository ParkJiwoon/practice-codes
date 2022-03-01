package com.bcp0109.spring_boot_aop._07_internal_call;

import org.springframework.stereotype.Component;

@Component
public class CallServiceV3 {

    /**
     * 구조를 변경해서 internal() 메서드를 따로 추출한 별도의 컴포넌트 서비스로 이동
     */

    private final InternalService internalService;

    public CallServiceV3(InternalService internalService) {
        this.internalService = internalService;
    }

    public void external() {
        System.out.println("call external");
        internalService.internal();
    }
}
