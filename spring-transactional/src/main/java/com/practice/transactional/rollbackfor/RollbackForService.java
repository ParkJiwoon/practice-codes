package com.practice.transactional.rollbackfor;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RollbackForService {

    private final MemberRepository memberRepository;

    @Transactional
    public void throwRuntimeException(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
        throw new RuntimeException();
    }

    @Transactional
    public void throwError(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
        throw new UnknownError();
    }

    @Transactional
    public void throwIOException(String name, Integer age) throws IOException {
        Member member = new Member(name, age);
        memberRepository.save(member);
        throw new IOException();
    }

    @Transactional(rollbackFor = IOException.class)
    public void throwRollbackForIOException(String name, Integer age) throws IOException {
        Member member = new Member(name, age);
        memberRepository.save(member);
        throw new IOException();
    }

    @Transactional(rollbackFor = {IOException.class, ClassNotFoundException.class})
    public void throwRuntimeExceptionTest(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
        throw new RuntimeException();
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public void noRollbackForRuntimeException(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
        throw new RuntimeException();
    }

    @Transactional(noRollbackFor = Error.class)
    public void noRollbackForError(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
        throw new UnknownError();
    }
}