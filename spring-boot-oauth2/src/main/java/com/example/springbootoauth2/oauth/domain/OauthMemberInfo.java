package com.example.springbootoauth2.oauth.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthMemberInfo {
    private final String email;
    private final String nickname;
    private final OauthType type;
}
