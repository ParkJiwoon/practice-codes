package com.tutorial.jwtsecurity.jwt;

import com.tutorial.jwtsecurity.controller.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

  private static final String AUTHORITIES_KEY = "auth";
  private static final String BEARER_TYPE = "bearer";
  private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;            // 1시간
  private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;      // 1일
  private static final Map<String, String> refreshTokens = new HashMap<>();
  public String getRefreshToken(String name) {
    return refreshTokens.get(name);
  }
  public void updateRefreshToken(String name, String token){
    refreshTokens.put(name,token);
  }
  private final Key key;

  public TokenProvider(@Value("${jwt.secret}") String secretKey) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public TokenDto generateTokenDto(Authentication authentication) {
    // 권한들 가져오기
    String authorities = authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));

    long now = (new Date()).getTime();

    // Access Token 생성
    Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
    String accessToken = Jwts.builder()
      .setSubject(authentication.getName())       // payload "sub": "name"
      .claim(AUTHORITIES_KEY, authorities)        // payload "auth": "ROLE_USER"
      .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
      .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
      .compact();


    // Refresh Token 생성
    String refreshToken = Jwts.builder()
      .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
      .signWith(key, SignatureAlgorithm.HS512)
      .compact();

    // Refresh Token 저장
    refreshTokens.put(authentication.getName(),refreshToken);

    return TokenDto.builder()
      .grantType(BEARER_TYPE)
      .accessToken(accessToken)
      .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
      .refreshToken(refreshToken)
      .build();
  }

  public Authentication getAuthentication(String accessToken) {
    // 토큰 복호화
    Claims claims = parseClaims(accessToken);

    if (claims.get(AUTHORITIES_KEY) == null) {
      throw new RuntimeException("권한 정보가 없는 토큰입니다.");
    }

    // 클레임에서 권한 정보 가져오기
    Collection<? extends GrantedAuthority> authorities =
      Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    // UserDetails 객체를 만들어서 Authentication 리턴
    UserDetails principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      log.info("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.info(e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.info("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.info("JWT 토큰이 잘못되었습니다.");
    }
    return false;
  }

  private Claims parseClaims(String accessToken) {
    try {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }
}
