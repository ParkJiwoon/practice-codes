package com.practice.transactional.readolny;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import com.practice.transactional.readonly.ReadOnlyFalseService;
import com.practice.transactional.readonly.ReadOnlyTrueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@DisplayName("@Transactional(readOnly = true) 테스트")
@SpringBootTest
public class ReadOnlyTrueTest {

    private static final String NAME = "alice";
    private static final Integer AGE = 23;

    @Autowired
    private ReadOnlyFalseService readOnlyFalseService;

    @Autowired
    private ReadOnlyTrueService service;

    @Autowired
    private MemberRepository repository;

    @DisplayName("일정한 환경을 위해 @Transactional 대신 @beforeEach 로 DB 클리어")
    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("INSERT 무시되어 값 생성되지 않음")
    @Test
    void testCreate() {
        service.create(NAME, AGE);
        assertThat(repository.findAll()).isEmpty();
    }

    @DisplayName("UPDATE 무시되어 값 수정되지 않음")
    @Test
    void testUpdate() {
        // 값 생성
        readOnlyFalseService.create(NAME, AGE);

        // readOnly = true 에서 UPDATE 호출
        service.updateName(NAME, NAME + " updated");

        // 기존 값이 그대로 남아있고 수정된 값은 없음
        assertThat(repository.findByName(NAME)).isPresent();
        assertThat(repository.findByName(NAME + " updated")).isEmpty();
    }

    @DisplayName("더티 체킹 무시되어 값 수정되지 않음")
    @Test
    void testDirtyChecking() {
        // 값 생성
        readOnlyFalseService.create(NAME, AGE);

        // readOnly = true 에서 더티 체킹 호출
        service.updateWithDirtyChecking(NAME, NAME + " updated");

        // 기존 값이 그대로 남아있고 수정된 값은 없음
        assertThat(repository.findByName(NAME)).isPresent();
        assertThat(repository.findByName(NAME + " updated")).isEmpty();
    }

    @DisplayName("DELETE 무시되어 값 수정되지 않음")
    @Test
    void testDelete() {
        readOnlyFalseService.create(NAME, AGE);

        Member member = repository.findByName(NAME).orElseThrow();
        service.delete(member);

        assertThat(repository.findByName(NAME)).isPresent();
    }
}
