package com.bcp0109.spring_boot_aop._04_proxy_factory.pointcut;

import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

public class MyMethodMatcher implements MethodMatcher {

    private static final String MATCH_NAME = "save";

    /**
     * 어드바이스를 적용할지 말지 판단
     *
     * @param method        호출하는 메서드
     * @param targetClass   호출하는 클래스
     * @return              포인트컷 적용 대상 여부
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        boolean result = method.getName().equals(MATCH_NAME);
        System.out.println("포인트컷 호출 method=" + method.getName());
        System.out.println("포인트컷 결과 result=" + result);
        return result;
    }

    /**
     * 런타임으로 판단할지 말지 결정
     * true 면 호출된 메서드의 매개변수로 넘어오는 값을 사용함. 아래에 있는 matches 메서드에서 판단
     * false 면 정적 정보 (클래스 이름, 메서드 이름) 만으로 판단
     */
    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }
}
