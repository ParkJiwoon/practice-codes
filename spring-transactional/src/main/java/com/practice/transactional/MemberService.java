package com.practice.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void create(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public void createWithReadOnlyTransactional(Long id, String name, Integer age) {
        Member member = new Member(id, name, age);
        memberRepository.save(member);
    }
}
