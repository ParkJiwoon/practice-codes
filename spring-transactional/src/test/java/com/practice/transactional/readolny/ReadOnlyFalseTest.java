package com.practice.transactional.readolny;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import com.practice.transactional.readonly.ReadOnlyFalseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("@Transactional(readOnly = false) 테스트")
@SpringBootTest
public class ReadOnlyFalseTest {

    private static final String NAME = "alice";
    private static final Integer AGE = 23;

    @Autowired
    private ReadOnlyFalseService service;

    @Autowired
    private MemberRepository repository;

    @DisplayName("일정한 환경을 위해 @Transactional 대신 @beforeEach 로 DB 클리어")
    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("정상적으로 값 DB 에 생성됨")
    @Test
    void testCreate() {
        service.create(NAME, AGE);

        List<Member> members = repository.findAll();

        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getName()).isEqualTo(NAME);
        assertThat(members.get(0).getAge()).isEqualTo(AGE);
    }

    @DisplayName("정상적으로 값 수정됨")
    @Test
    void testUpdate() {
        service.create(NAME, AGE);
        service.updateName(NAME, NAME + " updated");

        assertThat(repository.findByName(NAME + " updated")).isPresent();
    }

    @DisplayName("더티 체킹으로 정상적으로 값 수정됨")
    @Test
    void testDirtyChecking() {
        service.create(NAME, AGE);
        service.updateWithDirtyChecking(NAME, NAME + " updated");

        assertThat(repository.findByName(NAME + " updated")).isPresent();
    }

    @DisplayName("정상적으로 값 삭제됨")
    @Test
    void testDelete() {
        service.create(NAME, AGE);

        Member member = repository.findByName(NAME).orElseThrow();
        service.delete(member);

        assertThat(repository.findByName(NAME)).isEmpty();
    }
}
