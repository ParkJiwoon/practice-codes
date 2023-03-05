package com.example.springbootoauth2.authentication.domain.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthMemberInfo {
    private final String email;
    private final String nickname;
    private final OAuthType type;
}
