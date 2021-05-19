package com.practice.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoTransactionalService {

    private final MemberRepository memberRepository;

    public void create(String name, Integer age) {
        memberRepository.save(new Member(name, age));
    }

    public void updateWithDirtyChecking(String originalName, String newName) {
        Member member = memberRepository.findByName(originalName).orElseThrow();
        member.setName(newName);
    }
}
