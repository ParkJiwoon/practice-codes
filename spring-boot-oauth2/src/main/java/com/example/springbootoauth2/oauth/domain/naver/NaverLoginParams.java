package com.example.springbootoauth2.oauth.domain.naver;

import com.example.springbootoauth2.oauth.domain.client.OauthLoginParams;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class NaverLoginParams implements OauthLoginParams {
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
