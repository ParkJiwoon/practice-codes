package com.example.springbootoauth2.authentication.domain.oauth;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthType getOauthType();
}
