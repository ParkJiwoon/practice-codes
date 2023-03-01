package com.example.springbootoauth2.oauth.domain.client;

import com.example.springbootoauth2.oauth.domain.OauthType;

public interface OauthInfoResponse {
    String getEmail();
    String getNickname();
    OauthType getOauthType();
}
