package com.bcp0109.spring_boot_aop._02_jdk_dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeInvocationHandler implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(TimeInvocationHandler.class);

    // 동적 프록시가 호출할 대상
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = method.invoke(target, args);

        stopWatch.stop();
        log.info("TimeProxy 종료 resultTime={}", stopWatch.getTotalTimeMillis());
        return result;
    }
}
