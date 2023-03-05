package com.example.springbootoauth2.authentication.application;

import com.example.springbootoauth2.authentication.domain.AuthTokens;
import com.example.springbootoauth2.authentication.domain.AuthTokensGenerator;
import com.example.springbootoauth2.authentication.domain.oauth.OAuthInfoResponse;
import com.example.springbootoauth2.authentication.domain.oauth.OAuthLoginParams;
import com.example.springbootoauth2.member.domain.Member;
import com.example.springbootoauth2.member.domain.MemberRepository;
import com.example.springbootoauth2.authentication.domain.oauth.OAuthMemberInfo;
import com.example.springbootoauth2.authentication.domain.oauth.OAuthApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final OAuthApiClient kakaoApiClient;
    private final OAuthApiClient naverApiClient;

    public AuthTokens kakaoLogin(OAuthLoginParams params) {
        OAuthInfoResponse oauthInfoResponse = requestOauthInfo(kakaoApiClient, params);
        OAuthMemberInfo oauthMemberInfo = getOauthMemberInfo(oauthInfoResponse);
        return findMemberAndGenerateToken(oauthMemberInfo);
    }

    public AuthTokens naverLogin(OAuthLoginParams params) {
        OAuthInfoResponse oauthInfoResponse = requestOauthInfo(naverApiClient, params);
        OAuthMemberInfo oauthMemberInfo = getOauthMemberInfo(oauthInfoResponse);
        return findMemberAndGenerateToken(oauthMemberInfo);
    }

    private OAuthInfoResponse requestOauthInfo(OAuthApiClient client, OAuthLoginParams params) {
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }

    private OAuthMemberInfo getOauthMemberInfo(OAuthInfoResponse response) {
        return OAuthMemberInfo.builder()
                .email(response.getEmail())
                .nickname(response.getNickname())
                .type(response.getOauthType())
                .build();
    }

    private AuthTokens findMemberAndGenerateToken(OAuthMemberInfo oauthMemberInfo) {
        Long memberId = findOrCreateMember(oauthMemberInfo);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthMemberInfo oauthMemberInfo) {
        return memberRepository.findByEmail(oauthMemberInfo.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oauthMemberInfo));
    }

    private Long newMember(OAuthMemberInfo oauthMemberInfo) {
        Member member = Member.builder()
                .email(oauthMemberInfo.getEmail())
                .nickname(oauthMemberInfo.getNickname())
                .build();

        return memberRepository.save(member).getId();
    }
}
