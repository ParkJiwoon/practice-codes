package com.jiwoon.practicewebmvc.member;

public interface MemberRepository {
    Member findById(long memberId);
    void save(Member member);
}
