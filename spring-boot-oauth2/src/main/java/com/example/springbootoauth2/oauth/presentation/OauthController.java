package com.example.springbootoauth2.oauth.presentation;

import com.example.springbootoauth2.oauth.application.OauthLoginService;
import com.example.springbootoauth2.oauth.domain.JwtTokens;
import com.example.springbootoauth2.oauth.domain.kakao.KakaoLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthController {
    private final OauthLoginService oauthLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<JwtTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        JwtTokens jwtTokens = oauthLoginService.loginKakao(params);
        return ResponseEntity.ok(jwtTokens);
    }
}
