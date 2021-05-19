package com.practice.transactional.timeout;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TimeoutService {

    private final MemberRepository memberRepository;

    @Transactional(timeout = 2)
    public void timeout_2second_3sleep(String name, Integer age) throws InterruptedException {
        Member member = new Member(name, age);
        memberRepository.save(member);
        Thread.sleep(3000);
    }

    @Transactional(timeout = 3)
    public void timeout_2second_1sleep(String name, Integer age) throws InterruptedException {
        Member member = new Member(name, age);
        memberRepository.save(member);
        Thread.sleep(1000);
    }

    @Transactional(noRollbackFor = {RuntimeException.class, JpaSystemException.class}, timeout = 2)
    public void timeoutAndNoRollbackFor(String name, Integer age) throws InterruptedException {
        Member member = new Member(name, age);
        memberRepository.save(member);
        Thread.sleep(3000);
    }
}
