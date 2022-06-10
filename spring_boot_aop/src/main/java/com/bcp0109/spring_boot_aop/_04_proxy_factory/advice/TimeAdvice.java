package com.bcp0109.spring_boot_aop._04_proxy_factory.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class TimeAdvice implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TimeAdvice.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = invocation.proceed();

        stopWatch.stop();
        log.info("TimeProxy 종료 resultTime={}", stopWatch.getTotalTimeMillis());
        return result;
    }
}
