package com.example.springbootoauth2.oauth.domain.kakao;

import com.example.springbootoauth2.member.domain.MemberType;
import com.example.springbootoauth2.oauth.domain.client.OauthApiClient;
import com.example.springbootoauth2.oauth.domain.client.OauthLoginParams;
import com.example.springbootoauth2.oauth.domain.OauthMemberInfoOption;

public class KakaoMemberInfoOption implements OauthMemberInfoOption {
    private final MemberType type;
    private final KakaoApiClient client;
    private final KakaoLoginParams params;

    public KakaoMemberInfoOption(KakaoApiClient client, KakaoLoginParams params) {
        this.type = MemberType.KAKAO;
        this.client = client;
        this.params = params;
    }

    @Override
    public MemberType getType() {
        return type;
    }

    @Override
    public OauthApiClient getClient() {
        return client;
    }

    @Override
    public OauthLoginParams getParams() {
        return params;
    }
}
