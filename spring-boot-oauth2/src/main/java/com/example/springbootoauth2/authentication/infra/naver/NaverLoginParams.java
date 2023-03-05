package com.example.springbootoauth2.authentication.infra.naver;

import com.example.springbootoauth2.authentication.domain.oauth.OAuthLoginParams;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class NaverLoginParams implements OAuthLoginParams {
    private String grantType;
    private String clientId;
    private String authorizationCode;
    private String state;

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grantType);
        body.add("client_id", clientId);
        body.add("code", authorizationCode);
        body.add("state", state);
        return body;
    }
}
