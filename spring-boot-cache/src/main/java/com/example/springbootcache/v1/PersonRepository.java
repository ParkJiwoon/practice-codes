package com.example.springbootcache.v1;

public interface PersonRepository {
    Person save(Person person);
    Person find(Long id);
}
