package com.example.springbootoauth2.oauth.domain.client;

public interface OauthApiClient {
    String requestAccessToken(OauthLoginParams params);
    OauthInfoResponse requestOauthInfo(String accessToken);
}
