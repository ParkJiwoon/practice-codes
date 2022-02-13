package com.bcp0109.spring_boot_aop._03_cglib;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

public class CglibTest {

    @Test
    @DisplayName("CGLIB 를 사용해서 동적으로 프록시 객체 만들어서 호출")
    void cglib() {
        // 비즈니스 로직을 가진 타겟
        ConcreteService target = new ConcreteService();

        // CGLIB 에서 프록시를 생성하기 위하 제공하는 객체
        Enhancer enhancer = new Enhancer();

        // CGLIB 는 구체 클래스를 상속 받아서 프록시 생성. 상속받을 SuperClass 설정
        enhancer.setSuperclass(ConcreteService.class);

        // 프록시에 적용할 공통 로직 (MethodInterceptor 가 Callback 을 상속받음)
        enhancer.setCallback(new TimeMethodInterceptor(target));

        ConcreteService proxy = (ConcreteService) enhancer.create();

        System.out.println("targetClass=" + target.getClass());
        System.out.println("proxyClass=" + proxy.getClass());

        proxy.call();
    }
}
