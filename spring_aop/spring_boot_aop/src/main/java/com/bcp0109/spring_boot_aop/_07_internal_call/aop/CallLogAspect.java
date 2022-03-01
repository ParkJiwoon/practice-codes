package com.bcp0109.spring_boot_aop._07_internal_call.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CallLogAspect {

    @Before("execution(* com.bcp0109.spring_boot_aop._07_internal_call..*.*(..))")
    public void doLog(JoinPoint joinPoint) {
        System.out.println("aop=" + joinPoint.getSignature());
    }
}
