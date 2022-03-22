package com.example.springbootcache.v2;

import com.example.springbootcache.v1.PersonRepository;
import com.example.springbootcache.v1.RealPersonRepository;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppConfigV2 {

    @Bean
    public PersonRepository cachePersonRepository() {
        return new CachePersonRepository(
                new ConcurrentMapCache("local", new ConcurrentHashMap<>(), false),
                new RealPersonRepository()
        );
    }
}
