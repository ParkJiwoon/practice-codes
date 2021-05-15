package com.practice.transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ReadOnlyTest {

    private static final String NAME = "alice";
    private static final Integer AGE = 23;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("@Transactional 에서 INSERT 쿼리 정상적으로 호출")
    void testCreate() {
        // CREATE
        Member member = new Member(NAME, AGE);
        memberRepository.save(member);

        // SELECT
        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getName()).isEqualTo(NAME);
        assertThat(members.get(0).getAge()).isEqualTo(AGE);
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("@Transactional(readOnly = true) 에서 INSERT 쿼리 무시됨")
    void testCreateWithReadOnlyTransactional() {
        // CREATE
        Member member = new Member(NAME, AGE);
        memberRepository.save(member);

        // SELECT
        List<Member> members = memberRepository.findAll();

        assertThat(members).isEmpty();
    }

    @Test
    @DisplayName("readOnly = true 에서 save 를 하면 Entity 가 DB 에 저장되지 않음")
    void testReadOnlyAndFlush() {
        // CREATE
        memberService.createWithReadOnlyTransactional(1L, NAME, AGE);

        Optional<Member> byId = memberRepository.findById(1L);
        assertThat(byId.isPresent()).isFalse();

        Optional<Member> byName = memberRepository.findByName(NAME);
        assertThat(byName.isPresent()).isFalse();
    }

    // 현재 트랜잭션 이름 조회
    private void logCurrentTransactionName() {
        System.out.println("currentTransactionName : " + TransactionSynchronizationManager.getCurrentTransactionName());
    }

    // 영속성 컨텍스트에 올라가있는 지 조회
    private boolean isInPersistenceContext(Object entity) {
        return entityManager.contains(entity);
    }
}
