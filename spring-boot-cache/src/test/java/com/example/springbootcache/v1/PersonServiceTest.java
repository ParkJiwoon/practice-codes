package com.example.springbootcache.v1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    @DisplayName("PersonService 저장/찾기 테스트")
    void test() {
        // given
        Person person = new Person(1L, "woody", 30);

        // when
        personService.save(person);

        // then
        Person findPerson = personService.find(1L);
        assertThat(person).isEqualTo(findPerson);
    }
}
