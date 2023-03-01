package com.example.springbootoauth2.oauth.domain.client;

import org.springframework.util.MultiValueMap;

public interface OauthLoginParams {
    MultiValueMap<String, String> makeBody();
}
