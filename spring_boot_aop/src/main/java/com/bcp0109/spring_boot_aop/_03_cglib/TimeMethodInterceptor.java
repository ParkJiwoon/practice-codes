package com.bcp0109.spring_boot_aop._03_cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class TimeMethodInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TimeMethodInterceptor.class);
    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("TimeProxy 실행");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // method.invoke(target, args) 를 사용해도 되지만
        // CGLIB 는 성능상 proxy 에서 호출하는 것을 권장함
        Object result = proxy.invoke(target, args);

        stopWatch.stop();
        log.info("TimeProxy 종료 resultTime={}", stopWatch.getTotalTimeMillis());
        return result;
    }
}
