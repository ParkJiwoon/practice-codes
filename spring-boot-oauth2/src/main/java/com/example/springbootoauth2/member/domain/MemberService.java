package com.example.springbootoauth2.member.domain;

import com.example.springbootoauth2.member.infra.MemberRepository;
import com.example.springbootoauth2.oauth.domain.OauthMemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long findOrCreateMember(OauthMemberInfo oauthMemberInfo) {
        return memberRepository.findByEmail(oauthMemberInfo.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oauthMemberInfo));
    }

    private Long newMember(OauthMemberInfo oauthMemberInfo) {
        Member member = Member.builder()
                .email(oauthMemberInfo.getEmail())
                .nickname(oauthMemberInfo.getNickname())
                .build();

        return memberRepository.save(member).getId();
    }
}
