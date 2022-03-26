package com.example.springbootcache.config;

import com.example.springbootcache.v1.PersonRepository;
import com.example.springbootcache.v1.RealPersonRepository;
import com.example.springbootcache.v2.CachePersonRepository;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CacheConfig {

    @Bean
    public PersonRepository cachePersonRepository() {
        return new CachePersonRepository(
                new ConcurrentMapCache("local", new ConcurrentHashMap<>(), false),
                new RealPersonRepository()
        );
    }
}
