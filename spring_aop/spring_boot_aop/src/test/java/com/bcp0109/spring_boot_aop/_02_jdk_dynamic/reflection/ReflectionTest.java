package com.bcp0109.spring_boot_aop._02_jdk_dynamic.reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class ReflectionTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionTest.class);

    /**
     * JDK 동적 프록시를 활용하기 위한 Reflection 기술
     *
     * 클래스나 메서드의 메타 정보를 동적으로 가졍로 수 있음
     * 마지막 Reflection2 를 보면 서로 다른 메서드를 Method 라는 공통의 메서드로 추상화함
     *
     * 런타임에 동작하기 때문에 컴파일 시점에 오류를 잡을 수 없다는 단점이 있음
     */

    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }

    @Test
    @DisplayName("Reflection0: callA(), callB() 메서드 호출을 제외하고는 전후로 로직 중복이 존재")
    void reflection0() {
        Hello target = new Hello();

        // 공통 1 로직 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        // 공통 1 로직 종료

        // 공통 2 로직 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
        // 공통 2 로직 종료
    }

    @Test
    @DisplayName("Reflection1: 리플렉션 사용법 연습")
    void reflection1() throws Exception {
        // 클래스 정보
        Class<?> classHello = Class.forName("com.bcp0109.spring_boot_aop._02_jdk_dynamic.reflection.ReflectionTest$Hello");

        Hello target = new Hello();

        // callA 메서드 정보
        log.info("start");
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result={}", result1);

        // callB 메서드 정보
        log.info("start");
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result={}", result2);
    }

    @Test
    @DisplayName("Reflection2: 리플렉션을 활용해서 공통 로직을 별도 메서드로 분리")
    void reflection2() throws Exception {
        Hello target = new Hello();

        // callA 메서드 정보
        Method methodCallA = target.getClass().getMethod("callA");
        dynamicCall(methodCallA, target);

        // callB 메서드 정보
        Method methodCallB = target.getClass().getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }
}
