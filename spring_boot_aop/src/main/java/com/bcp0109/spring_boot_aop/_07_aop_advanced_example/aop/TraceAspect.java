package com.bcp0109.spring_boot_aop._07_aop_advanced_example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class TraceAspect {

    @Before("@annotation(com.bcp0109.spring_boot_aop._07_aop_advanced_example.annotation.Trace)")
    public void doTrace(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("[trace] " + joinPoint.getSignature() + ", args=" + Arrays.toString(args));
    }
}
