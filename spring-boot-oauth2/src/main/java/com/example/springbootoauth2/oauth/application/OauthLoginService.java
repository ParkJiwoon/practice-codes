package com.example.springbootoauth2.oauth.application;

import com.example.springbootoauth2.member.domain.Member;
import com.example.springbootoauth2.member.domain.MemberService;
import com.example.springbootoauth2.oauth.domain.JwtTokenGenerator;
import com.example.springbootoauth2.oauth.domain.JwtTokens;
import com.example.springbootoauth2.oauth.domain.OauthMemberInfo;
import com.example.springbootoauth2.oauth.domain.kakao.KakaoApiClient;
import com.example.springbootoauth2.oauth.domain.kakao.KakaoLoginParams;
import com.example.springbootoauth2.oauth.domain.kakao.KakaoMemberInfoOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {
    private final MemberService memberService;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final KakaoApiClient kakaoApiClient;

    public JwtTokens loginKakao(KakaoLoginParams params) {
        KakaoMemberInfoOption option = new KakaoMemberInfoOption(kakaoApiClient, params);
        OauthMemberInfo oauthMemberInfo = OauthMemberInfo.newInstance(option);
        Member member = memberService.findOrCreateMember(oauthMemberInfo);
        return jwtTokenGenerator.generate(member.getId().toString());
    }
}
