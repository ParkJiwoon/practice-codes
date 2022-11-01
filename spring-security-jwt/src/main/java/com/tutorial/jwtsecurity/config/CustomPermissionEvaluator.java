package com.tutorial.jwtsecurity.config;

import com.tutorial.jwtsecurity.entity.Member;
import com.tutorial.jwtsecurity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {
  private final MemberRepository repository;

  @Override
  public boolean hasPermission(Authentication authentication, Object email, Object role) {
    Member target = repository.findByEmail((String) email).orElseThrow(NoSuchElementException::new);
    // 서로의 권한이 동일하다면 접근할 수 없다
    if (authentication.getAuthorities().equals(target.getAuthorities())) {
      return false;
      // 서로의 권한이 다르면서 요구된 권한과 일치한다면 true 를 리턴하여 서비스를 인가받는다.
    } else {
      return checkRole(authentication, (String) role);
    }
  }

  // 서비스 이용객체의 권한과 요구된 권한이 일치하는지 확인하는 메소드
  private boolean checkRole(Authentication authentication, String role) {
    return authentication.getAuthorities().equals(new ArrayList<>(List.of(new SimpleGrantedAuthority(role))));
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
    return false;
  }


}