package com.bcp0109.spring_boot_aop._02_jdk_dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyTest {

    /**
     * JDK 동적 프록시 실행 순서
     *
     * 1. 클라이언트는 JDK 동적 프록시 proxy.call() 실행
     * 2. 프록시 내부에서 InvocationHandler.invoke() 실행
     * 3. 여기서는 InvocationHandler 를 구현한 TimeInvocationHandler 의 invoke() 가 실행됨
     * 4. invoke() 내부에서는 target 의 call() 메서드 실행
     */
    @Test
    @DisplayName("JDK 동적 프록시 예제")
    void dynamic() {
        SampleInterface target = new SampleImpl();
        InvocationHandler handler = new TimeInvocationHandler(target);

        SampleInterface proxy = (SampleInterface) Proxy.newProxyInstance(
                SampleInterface.class.getClassLoader(),
                new Class[]{SampleInterface.class},
                handler);

        proxy.foo();
        System.out.println("targetClass = " + target.getClass());
        System.out.println("proxyClass = " + proxy.getClass());
    }


    @Test
    @DisplayName("JDK 동적 프록시로 AImpl 의 메서드 호출")
    void dynamicA() {
        // 동적 프록시가 호출할 대상
        AInterface target = new AImpl();
        InvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                                                               new Class[]{AInterface.class},
                                                               handler);

        proxy.call();
        System.out.println("targetClass = " + target.getClass());
        System.out.println("proxyClass = " + proxy.getClass());
    }

    @Test
    @DisplayName("JDK 동적 프록시로 BImpl 의 메서드 호출")
    void dynamicB() {
        // 동적 프록시가 호출할 대상
        BInterface target = new BImpl();
        InvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                handler);

        proxy.call();
        System.out.println("targetClass = " + target.getClass());
        System.out.println("proxyClass = " + proxy.getClass());
    }
}
