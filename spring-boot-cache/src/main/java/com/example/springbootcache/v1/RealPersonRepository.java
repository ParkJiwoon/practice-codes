package com.example.springbootcache.v1;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RealPersonRepository implements PersonRepository {
    private final Map<Long, Person> store = new ConcurrentHashMap<>();

    @Override
    public Person save(Person person) {
        log.info("RealPersonRepository -> save person={}", person);
        store.put(person.getId(), person);
        return person;
    }

    @Override
    public Person find(Long id) {
        log.info("RealPersonRepository -> find id={}", id);
        return store.get(id);
    }
}
