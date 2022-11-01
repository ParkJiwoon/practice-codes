package com.tutorial.jwtsecurity.controller;


import com.tutorial.jwtsecurity.controller.dto.MemberRequestDto;
import com.tutorial.jwtsecurity.controller.dto.MemberResponseDto;
import com.tutorial.jwtsecurity.controller.dto.TokenRequestDto;
import com.tutorial.jwtsecurity.controller.dto.TokenDto;
import com.tutorial.jwtsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
    return ResponseEntity.ok(authService.signup(memberRequestDto));
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody MemberRequestDto memberRequestDto) {
    return ResponseEntity.ok()
      .headers(authService.login(memberRequestDto))
      .body("로그인 성공");
  }

  @PostMapping("/reissue")
  public ResponseEntity<String> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
    return ResponseEntity.ok()
      .headers(authService.reissue(tokenRequestDto))
      .body("재발급 성공");
  }
}
