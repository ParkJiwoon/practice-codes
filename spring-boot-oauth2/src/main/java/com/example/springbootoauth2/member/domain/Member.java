package com.example.springbootoauth2.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private MemberType type;

    @Builder
    public Member(String email, String nickname, MemberType type) {
        this.email = email;
        this.nickname = nickname;
        this.type = type;
    }
}
