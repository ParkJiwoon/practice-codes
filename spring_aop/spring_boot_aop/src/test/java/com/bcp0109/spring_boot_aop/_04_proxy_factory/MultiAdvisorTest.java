package com.bcp0109.spring_boot_aop._04_proxy_factory;

import com.bcp0109.spring_boot_aop._04_proxy_factory.advice.ServiceImpl;
import com.bcp0109.spring_boot_aop._04_proxy_factory.advice.ServiceInterface;
import com.bcp0109.spring_boot_aop._04_proxy_factory.multi_advice.Advice1;
import com.bcp0109.spring_boot_aop._04_proxy_factory.multi_advice.Advice2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MultiAdvisorTest {

    @Test
    @DisplayName("여러 프록시: 프록시를 여러개 생성해야하는 문제가 있음. 웬만하면 어드바이저를 여러개 사용" +
            "client -> proxy2(advisor2) -> proxy1(advisor1) -> target")
    void multiAdvisorTest1() {
        ServiceInterface target = new ServiceImpl();

        // 프록시 1 생성
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        // 프록시 2 생성
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory2.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        // 실행
        proxy2.call();
    }

    @Test
    @DisplayName("하나의 프록시, 여러 어드바이저" +
            "client -> proxy -> advisor1 -> advisor2 -> target")
    void multiAdvisorTest2() {
        // advisor 두개 생성
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // advisor 를 추가한 순서대로 실행됨
        proxyFactory.addAdvisor(advisor1);
        proxyFactory.addAdvisor(advisor2);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        // 실행
        proxy.call();
    }
}
