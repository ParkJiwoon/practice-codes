package com.example.springbootoauth2.oauth.domain;

import com.example.springbootoauth2.member.domain.MemberType;
import com.example.springbootoauth2.oauth.domain.client.OauthApiClient;
import com.example.springbootoauth2.oauth.domain.client.OauthInfoResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthMemberInfo {
    private final String email;
    private final String nickname;
    private final MemberType type;

    public static OauthMemberInfo newInstance(OauthMemberInfoOption option) {
        OauthApiClient client = option.getClient();

        String accessToken = client.requestAccessToken(option.getParams());
        OauthInfoResponse response = client.requestOauthInfo(accessToken);

        return OauthMemberInfo.builder()
                .email(response.getEmail())
                .nickname(response.getNickname())
                .type(option.getType())
                .build();
    }
}
