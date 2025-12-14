package com.example.demo.controller;

import com.example.demo.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final JwtUtil jwtUtil;

  // 로그인 요청 (프론트에서 JSON POST 요청)
  @PostMapping("/login")
  public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
    // 1. 사용자 인증 (DB 조회 및 비밀번호 일치 확인 - 생략)
    if (!loginRequest.username().equals("user") || !loginRequest.password().equals("pass")) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return "Invalid Credentials";
    }

    // 2. JWT 생성
    String jwt = jwtUtil.generateToken(loginRequest.username());

    // 3. HttpOnly 쿠키 생성 및 설정
    Cookie jwtCookie = new Cookie("access_token", jwt);
    jwtCookie.setHttpOnly(true); // JS 접근 차단 (XSS 방어)
    jwtCookie.setSecure(false);  // 테스트 환경이므로 false. 실무에서는 true 필수!
    jwtCookie.setMaxAge(30 * 60); // 30분 유효 기간
    jwtCookie.setPath("/");

    // 4. 응답 헤더에 쿠키 추가
    response.addCookie(jwtCookie);

    return "Login successful! JWT set in HttpOnly Cookie.";
  }

  // 토큰 테스트용 API (쿠키에 담긴 JWT를 확인)
  @GetMapping("/secure")
  public String secureEndpoint(@CookieValue(name = "access_token", required = false) String token) {
    if (token == null) {
      return "Access Denied: No access_token cookie found.";
    }

    // 5. 서버에서 쿠키의 JWT를 파싱하고 인증 로직 수행 (파싱 로직은 생략)
    // String username = jwtUtil.getUsernameFromToken(token);

    return "Access Granted! Your token from cookie: " + token;
  }
}

// DTO는 단순 record로 정의
record LoginRequest(String username, String password) {}
