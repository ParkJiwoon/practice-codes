package com.bcp0109.spring_boot_aop._04_proxy_factory.multi_advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Advice2 implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("advice2 호출");
        return invocation.proceed();
    }
}
