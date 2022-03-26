package com.example.springbootcache.v3;

import com.example.springbootcache.v1.Person;
import com.example.springbootcache.v1.PersonRepository;
import com.example.springbootcache.v1.RealPersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@Import(CacheAspect.class)
@SpringBootTest
public class CacheablePersonRepositoryMockTest {

    @Autowired
    private PersonRepository cacheablePersonRepository;

    @MockBean
    private RealPersonRepository repo;

    @Test
    @DisplayName("캐싱된 find 를 여러번 호출해도 내부의 RealRepo 는 한번만 호출됨")
    void test() {
        // given
        Person person = new Person(1L, "woody", 30);
        given(repo.find(anyLong())).willReturn(person);

        // when
        cacheablePersonRepository.save(person);

        // then : 한번 읽어서 캐시에 등록한 후 두번째 읽은 데이터로 비교
        cacheablePersonRepository.find(1L);
        Person findPerson = cacheablePersonRepository.find(1L);
        assertThat(person).isEqualTo(findPerson);

        then(repo).should(times(1)).find(anyLong());
    }
}
