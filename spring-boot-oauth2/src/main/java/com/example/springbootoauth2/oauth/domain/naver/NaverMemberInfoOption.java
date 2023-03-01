package com.example.springbootoauth2.oauth.domain.naver;

import com.example.springbootoauth2.member.domain.MemberType;
import com.example.springbootoauth2.oauth.domain.client.OauthApiClient;
import com.example.springbootoauth2.oauth.domain.client.OauthLoginParams;
import com.example.springbootoauth2.oauth.domain.OauthMemberInfoOption;

public class NaverMemberInfoOption implements OauthMemberInfoOption {
    private final MemberType type;
    private final NaverApiClient client;
    private final NaverLoginParams params;

    public NaverMemberInfoOption(NaverApiClient client, NaverLoginParams params) {
        this.type = MemberType.NAVER;
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
