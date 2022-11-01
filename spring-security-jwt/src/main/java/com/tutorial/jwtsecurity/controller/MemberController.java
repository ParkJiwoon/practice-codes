package com.tutorial.jwtsecurity.controller;

import com.tutorial.jwtsecurity.controller.dto.MemberResponseDto;
import com.tutorial.jwtsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/me")
  public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
    return ResponseEntity.ok(memberService.getMyInfo());
  }

  @GetMapping("/{email}")
  @PreAuthorize("hasPermission(#email,'ROLE_ADMIN')")
  public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String email) {
    return ResponseEntity.ok(memberService.getMemberInfo(email));
  }
}