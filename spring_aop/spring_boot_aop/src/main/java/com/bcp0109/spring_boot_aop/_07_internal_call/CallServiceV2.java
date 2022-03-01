package com.bcp0109.spring_boot_aop._07_internal_call;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class CallServiceV2 {

    /**
     * 프록시와 내부호출 해결법 - 지연 조회
     *
     * 셀프 주입과 비슷하지만 메서드 호출 시점에 빈을 꺼낸다는게 차이점
     * 순환 참조가 발생하지 않음
     */

//    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        System.out.println("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal(); // 외부 메서드 호출
    }

    public void internal() {
        System.out.println("call internal");
    }
}
