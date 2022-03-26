package com.example.springbootcache.v1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RealPersonRepositoryTest {

    @Autowired
    private PersonRepository realPersonRepository;

    @Test
    @DisplayName("RealPersonRepository 저장/찾기 테스트")
    void test() {
        // given
        Person person = new Person(1L, "woody", 30);

        // when
        realPersonRepository.save(person);

        // then
        Person findPerson = realPersonRepository.find(1L);
        assertThat(person).isEqualTo(findPerson);
    }
}
