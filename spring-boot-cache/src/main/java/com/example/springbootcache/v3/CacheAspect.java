package com.example.springbootcache.v3;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cache.Cache;

@Slf4j
@Aspect
public class CacheAspect {

    /**
     * 읽기 캐시 적용
     * 메서드의 리턴 타입과 파라미터는 어떻게 들어올지 모른다
     * 그렇기 때문에 key 는 어노테이션에 지정된 걸 사용하자
     *
     * @param joinPoint 읽기 캐시 적용하는 곳
     * @param cacheable 저장소 이름 (cacheName) 과 키값 (key) 받는 어노테이션
     */
    @Around("@annotation(cacheable)")
    public Object doCache(ProceedingJoinPoint joinPoint, CustomCacheable cacheable) throws Throwable {
        Cache cache = getCache(cacheable);
        String key = joinPoint.getSignature() + cacheable.key();
        Cache.ValueWrapper valueWrapper = cache.get(key);

        if (valueWrapper != null) {
            log.info("[CustomCacheable] caching key: {}", key);
            return valueWrapper.get();
        }

        Object result = joinPoint.proceed();
        cache.put(key, result);
        log.info("[CustomCacheable] result {}", result);
        return result;
    }

    private Cache getCache(CustomCacheable cacheable) {
        String cacheName = cacheable.cacheName();
        return CacheFactory.getCache(cacheName);
    }
}
