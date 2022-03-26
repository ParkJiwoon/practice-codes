package com.example.springbootcache.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CacheFactory {
    private static final Map<String, Cache> caches = new HashMap<>();

    public static Cache getCache(String cacheName) {
        log.info("CacheFactory getCache {}", cacheName);
        if (caches.containsKey(cacheName)) {
            return caches.get(cacheName);
        }

        log.info("CacheFactory create new cache {}", cacheName);
        Cache cache = new ConcurrentMapCache(cacheName, new ConcurrentHashMap<>(), false);
        caches.put(cacheName, cache);
        return cache;
    }
}
