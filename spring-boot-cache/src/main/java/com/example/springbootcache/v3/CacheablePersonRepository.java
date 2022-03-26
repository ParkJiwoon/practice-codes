package com.example.springbootcache.v3;

import com.example.springbootcache.v1.Person;
import com.example.springbootcache.v1.PersonRepository;
import com.example.springbootcache.v1.RealPersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CacheablePersonRepository implements PersonRepository {

    private final RealPersonRepository repo;

    @Override
    public Person save(Person person) {
        log.info("CacheablePersonRepository -> save person={}, class={}", person, getClass());
        return repo.save(person);
    }

    @Override
    @CustomCacheable
    public Person find(Long id) {
        log.info("CacheablePersonRepository -> find id={}", id);
        return repo.find(id);
    }
}
