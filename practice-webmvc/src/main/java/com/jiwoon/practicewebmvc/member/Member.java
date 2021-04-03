package com.jiwoon.practicewebmvc.member;

import lombok.Getter;

@Getter
public class Member {
    Long memberId;
    String name;
    Grade grade;

    public Member(long memberId, String name, Grade grade) {
        this.memberId = memberId;
        this.name = name;
        this.grade = grade;
    }
}
