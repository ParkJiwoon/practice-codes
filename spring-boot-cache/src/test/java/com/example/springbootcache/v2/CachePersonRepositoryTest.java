package com.example.springbootcache.v2;

import com.example.springbootcache.v1.Person;
import com.example.springbootcache.v1.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CachePersonRepositoryTest {

    @Autowired
    private PersonRepository cachePersonRepository;

    @Test
    @DisplayName("캐시 적용한 PersonRepository 저장/찾기 테스트 하면 찾을 때 Real Repo 는 조회 안함")
    void test() {
        // given
        Person person = new Person(1L, "woody", 30);

        // when
        cachePersonRepository.save(person);

        // then
        Person findPerson = cachePersonRepository.find(1L);
        assertThat(person).isEqualTo(findPerson);
    }
}
