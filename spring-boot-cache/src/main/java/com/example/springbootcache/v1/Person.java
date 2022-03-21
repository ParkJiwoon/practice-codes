package com.example.springbootcache.v1;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Person {
    private final Long id;
    private final String name;
    private final Integer age;
    private final LocalDateTime createdTime;

    public Person(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdTime = LocalDateTime.now();
    }
}
