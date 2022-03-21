package com.example.springbootcache.v2;

import com.example.springbootcache.v1.Person;
import com.example.springbootcache.v1.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;

@Slf4j
@RequiredArgsConstructor
public class CachePersonRepository implements PersonRepository {

    private final Cache cache;
    private final PersonRepository repo;

    @Override
    public Person save(Person person) {
        log.info("CachePersonRepository -> save person={}", person);
        Person savedPerson = repo.save(person);
        cache.put(savedPerson.getId(), savedPerson);
        return savedPerson;
    }

    /**
     * 캐시 먼저 조회
     */
    @Override
    public Person find(Long id) {
        log.info("CachePersonRepository -> find id={}", id);
        Person person = cache.get(id, Person.class);

        if (person == null) {
            person = repo.find(id);
            cache.put(person.getId(), person);
        }

        return person;
    }
}
