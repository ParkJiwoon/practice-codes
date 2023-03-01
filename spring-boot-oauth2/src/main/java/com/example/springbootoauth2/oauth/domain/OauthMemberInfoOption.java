package com.example.springbootoauth2.oauth.domain;

import com.example.springbootoauth2.member.domain.MemberType;
import com.example.springbootoauth2.oauth.domain.client.OauthApiClient;
import com.example.springbootoauth2.oauth.domain.client.OauthLoginParams;

public interface OauthMemberInfoOption {
    MemberType getType();
    OauthApiClient getClient();
    OauthLoginParams getParams();
}
