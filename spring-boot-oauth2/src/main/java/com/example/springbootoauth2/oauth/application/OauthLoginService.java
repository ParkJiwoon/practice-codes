package com.example.springbootoauth2.oauth.application;

import com.example.springbootoauth2.member.domain.MemberService;
import com.example.springbootoauth2.oauth.domain.JwtTokenGenerator;
import com.example.springbootoauth2.oauth.domain.JwtTokens;
import com.example.springbootoauth2.oauth.domain.OauthMemberInfo;
import com.example.springbootoauth2.oauth.domain.OauthType;
import com.example.springbootoauth2.oauth.domain.client.OauthApiClient;
import com.example.springbootoauth2.oauth.domain.client.OauthInfoResponse;
import com.example.springbootoauth2.oauth.domain.client.OauthLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {
    private final MemberService memberService;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final OauthApiClient kakaoApiClient;
    private final OauthApiClient naverApiClient;

    public JwtTokens loginKakao(OauthLoginParams params) {
        OauthInfoResponse oauthInfoResponse = requestOauthInfo(kakaoApiClient, params);
        OauthMemberInfo oauthMemberInfo = getOauthMemberInfo(oauthInfoResponse);
        return findMemberAndGenerateToken(oauthMemberInfo);
    }

    public JwtTokens lognNaver(OauthLoginParams params) {
        OauthInfoResponse oauthInfoResponse = requestOauthInfo(naverApiClient, params);
        OauthMemberInfo oauthMemberInfo = getOauthMemberInfo(oauthInfoResponse);
        return findMemberAndGenerateToken(oauthMemberInfo);
    }

    private OauthInfoResponse requestOauthInfo(OauthApiClient client, OauthLoginParams params) {
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }

    private OauthMemberInfo getOauthMemberInfo(OauthInfoResponse response) {
        return OauthMemberInfo.builder()
                .email(response.getEmail())
                .nickname(response.getNickname())
                .type(response.getOauthType())
                .build();
    }

    private JwtTokens findMemberAndGenerateToken(OauthMemberInfo oauthMemberInfo) {
        Long memberId = memberService.findOrCreateMember(oauthMemberInfo);
        return jwtTokenGenerator.generate(memberId.toString());
    }
}
