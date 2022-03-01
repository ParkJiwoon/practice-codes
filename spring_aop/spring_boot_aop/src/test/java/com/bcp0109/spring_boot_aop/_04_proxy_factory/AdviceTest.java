package com.bcp0109.spring_boot_aop._04_proxy_factory;

import com.bcp0109.spring_boot_aop._04_proxy_factory.advice.ConcreteService;
import com.bcp0109.spring_boot_aop._04_proxy_factory.advice.ServiceImpl;
import com.bcp0109.spring_boot_aop._04_proxy_factory.advice.ServiceInterface;
import com.bcp0109.spring_boot_aop._04_proxy_factory.advice.TimeAdvice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.*;

class AdviceTest {

    @Test
    @DisplayName("target 객체에 인터페이스가 존재하면 JDK 동적 프록시로 생성됨")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();

        // target 인스턴스 정보로 프록시 생성 (인터페이스 존재 - JDK, 구체 클래스만 존재 - CGLIB)
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 프록시가 사용할 부가 기능 추가
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        System.out.println("targetClass=" + target.getClass());
        System.out.println("proxyClass=" + proxy.getClass());

        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("target 객체에 인터페이스 없이 클래스만 존재한다면 CGLIB 로 생성됨")
    void concreteProxy() {
        ConcreteService target = new ConcreteService();

        // target 인스턴스 정보로 프록시 생성 (인터페이스 존재 - JDK, 구체 클래스만 존재 - CGLIB)
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 프록시가 사용할 부가 기능 추가
        proxyFactory.addAdvice(new TimeAdvice());

        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        System.out.println("targetClass=" + target.getClass());
        System.out.println("proxyClass=" + proxy.getClass());

        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB 를 사용")
    void proxyTargetClass() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        System.out.println("targetClass=" + target.getClass());
        System.out.println("proxyClass=" + proxy.getClass());

        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
