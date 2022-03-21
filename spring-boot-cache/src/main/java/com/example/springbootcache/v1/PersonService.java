package com.example.springbootcache.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final RealPersonRepository realPersonRepository;

    public Person save(Person person) {
        log.info("PersonService -> save person={}", person);
        return realPersonRepository.save(person);
    }

    public Person find(Long id) {
        Person person = realPersonRepository.find(id);
        log.info("PersonService -> find person={}", person);
        return person;
    }
}
