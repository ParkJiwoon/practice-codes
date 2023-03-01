package com.example.springbootoauth2.oauth.domain.kakao;

import com.example.springbootoauth2.oauth.domain.client.OauthLoginParams;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OauthLoginParams {
    private String grantType;
    private String clientId;
    private String authorizationCode;

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grantType);
        body.add("client_id", clientId);
        body.add("code", authorizationCode);
        return body;
    }
}
