package com.bcp0109.spring_boot_aop._07_internal_call;

import com.bcp0109.spring_boot_aop._07_internal_call.aop.CallLogAspect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    @DisplayName("external 에는 AOP 가 걸리지만 내부에서 호출하는 internal 에는 AOP 가 적용되지 않음")
    void external() {
        callServiceV0.external();
    }

    @Test
    @DisplayName("외부에서 호출하는 internal 에는 AOP 가 적용됨")
    void internal() {
        callServiceV0.internal();
    }
}
