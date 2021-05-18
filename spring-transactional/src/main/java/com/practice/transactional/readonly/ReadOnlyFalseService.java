package com.practice.transactional.readonly;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadOnlyFalseService {

    private final MemberRepository memberRepository;

    public void create(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
    }

    public void updateName(String originalName, String newName) {
        Member member = memberRepository.findByName(originalName).orElseThrow();
        member.setName(newName);
        memberRepository.save(member);
    }

    public void updateWithDirtyChecking(String originalName, String newName) {
        Member member = memberRepository.findByName(originalName).orElseThrow();
        member.setName(newName);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
