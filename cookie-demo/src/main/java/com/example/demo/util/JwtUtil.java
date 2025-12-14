package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  // 1. 키를 String 대신 SecretKey 객체로 저장
  private final SecretKey SIGNING_KEY;
  private final long EXPIRATION_TIME = 1000 * 60 * 30; // 30분 (밀리초 단위)

  // 2. 생성자에서 안전한 키를 생성
  public JwtUtil() {
    // 보안상 안전한 무작위 256비트(32바이트) 키를 생성 (HS256 최소 요구 사항)
    // 이 키를 저장소에 보관하거나, Base64 인코딩하여 환경 변수에서 로드
    // 여기서는 임시로 하드코딩된 문자열을 Base64 디코딩하여 키로 사용
    String base64Secret = "ThisIsASecureKeyForHS256JWTTestMustBeAtLeast32Bytes";

    // Base64 인코딩된 문자열을 사용하여 키를 생성
    SIGNING_KEY = Keys.hmacShaKeyFor(base64Secret.getBytes());
  }

  public String generateToken(String username) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SIGNING_KEY) // SignatureAlgorithm은 Key에 내포됨
        .compact();
  }

}