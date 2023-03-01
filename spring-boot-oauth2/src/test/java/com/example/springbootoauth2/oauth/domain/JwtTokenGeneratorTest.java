package com.example.springbootoauth2.oauth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtTokenGeneratorTest {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Test
    @DisplayName("JWT 토큰 생성 성공")
    void testGenerate() {
        // given
        String subject = "test-subject";

        // when
        JwtTokens jwtTokens = jwtTokenGenerator.generate(subject);

        // then
        assertThat(jwtTokens.getGrantType()).isEqualTo("Bearer");
        assertThat(jwtTokens.getAccessToken()).isNotBlank();
        assertThat(jwtTokens.getRefreshToken()).isNotBlank();
        assertThat(jwtTokens.getAccessTokenExpiresIn()).isNotNull();
    }

    @Test
    @DisplayName("JWT 토큰 검증 성공")
    void testExtractSubject() {
        // given
        String subject = "test-subject";
        JwtTokens jwtTokens = jwtTokenGenerator.generate(subject);
        String accessToken = jwtTokens.getAccessToken();

        // when
        String extractSubject = jwtTokenGenerator.extractSubject(accessToken);

        // then
        assertThat(extractSubject).isEqualTo(subject);
    }
}
