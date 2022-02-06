package com.bcp0109.spring_boot_aop._02_internal_call;

import com.bcp0109.spring_boot_aop._02_internal_call.aop.CallLogAspect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV3Test {

    @Autowired
    CallServiceV3 callServiceV3;

    @Test
    @DisplayName("내부 호출을 없애고 InternalService 라는 별도의 컴포넌트를 만들었기 때문에 AOP 적용되어 있음")
    void external() {
        callServiceV3.external();
    }
}
