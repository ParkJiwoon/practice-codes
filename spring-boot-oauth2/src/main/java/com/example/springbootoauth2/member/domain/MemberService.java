package com.example.springbootoauth2.member.domain;

import com.example.springbootoauth2.member.infra.MemberRepository;
import com.example.springbootoauth2.oauth.domain.OauthMemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findOrCreateMember(OauthMemberInfo oauthMemberInfo) {
        return memberRepository.findByEmail(oauthMemberInfo.getEmail())
                .orElseGet(() -> newMember(oauthMemberInfo));
    }

    private Member newMember(OauthMemberInfo oauthMemberInfo) {
        Member member = Member.builder()
                .email(oauthMemberInfo.getEmail())
                .nickname(oauthMemberInfo.getNickname())
                .type(oauthMemberInfo.getType())
                .build();

        return memberRepository.save(member);
    }
}
