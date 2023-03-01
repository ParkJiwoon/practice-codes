package com.example.springbootoauth2.oauth.application;

import com.example.springbootoauth2.member.domain.Member;
import com.example.springbootoauth2.member.domain.MemberService;
import com.example.springbootoauth2.oauth.domain.JwtTokenGenerator;
import com.example.springbootoauth2.oauth.domain.JwtTokens;
import com.example.springbootoauth2.oauth.domain.OauthMemberInfo;
import com.example.springbootoauth2.oauth.domain.naver.NaverApiClient;
import com.example.springbootoauth2.oauth.domain.kakao.KakaoApiClient;
import com.example.springbootoauth2.oauth.domain.kakao.KakaoLoginParams;
import com.example.springbootoauth2.oauth.domain.kakao.KakaoMemberInfoOption;
import com.example.springbootoauth2.oauth.domain.naver.NaverLoginParams;
import com.example.springbootoauth2.oauth.domain.naver.NaverMemberInfoOption;
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
    private final NaverApiClient naverApiClient;

    public JwtTokens loginKakao(KakaoLoginParams params) {
        KakaoMemberInfoOption option = new KakaoMemberInfoOption(kakaoApiClient, params);
        return findMemberAndGenerateToken(OauthMemberInfo.newInstance(option));
    }

    public JwtTokens lognNaver(NaverLoginParams params) {
        NaverMemberInfoOption option = new NaverMemberInfoOption(naverApiClient, params);
        return findMemberAndGenerateToken(OauthMemberInfo.newInstance(option));
    }

    private JwtTokens findMemberAndGenerateToken(OauthMemberInfo oauthMemberInfo) {
        Member member = memberService.findOrCreateMember(oauthMemberInfo);
        return jwtTokenGenerator.generate(member.getId().toString());
    }
}
