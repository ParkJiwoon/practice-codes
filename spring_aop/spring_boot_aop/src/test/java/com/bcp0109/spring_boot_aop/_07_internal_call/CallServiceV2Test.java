package com.bcp0109.spring_boot_aop._07_internal_call;

import com.bcp0109.spring_boot_aop._07_internal_call.aop.CallLogAspect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV2Test {

    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    @DisplayName("Bean 을 호출 시점에 꺼내서 호출함. 인스턴스로 호출하기 때문에 AOP 적용되어 있음")
    void external() {
        callServiceV2.external();
    }

    @Test
    @DisplayName("외부에서 호출하는 internal 에는 AOP 가 적용됨")
    void internal() {
        callServiceV2.internal();
    }
}
