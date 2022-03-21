package com.example.springbootcache.v1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigV1 {

    @Bean
    public PersonService personService() {
        return new PersonService(personRepository());
    }

    @Bean
    public RealPersonRepository personRepository() {
        return new RealPersonRepository();
    }
}
