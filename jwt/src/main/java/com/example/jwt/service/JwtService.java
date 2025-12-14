package com.example.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtService {

  private static final String SECRET_KEY = "TG9yZW0gSXBzdW0gaXMgc2ltcGx5IGR1bW15IHRleHQgb2YgdGhlIHByaW50aW5nIGFuZCB0eXBlc2V0dGluZyBpbmR1c3RyeS4";
  private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

  public String generateToken(
      Map<String, Object> claims,
      LocalDateTime localExpiredAt
  ) {
    Date dateExpiredAt = Date.from(localExpiredAt.atZone(ZoneId.systemDefault()).toInstant());

    return Jwts.builder()
        .signWith(KEY, SignatureAlgorithm.HS256) // SecretKey와 Algorithm을 통해 서명
        .setClaims(claims) // 사용자 정보를 Payload에 설정
        .setExpiration(dateExpiredAt)
        .compact();
  }

  public void validate(String jwt) {
    JwtParser jwtParser = Jwts.parserBuilder() // 서명 검증을 하는 Parser 생성
        .setSigningKey(KEY) // Secret Key 설정
        .build();

    /*
    * 1. 서명 검증
    * 2. 만료 시간 검증
    * */
    try {
      Jws<Claims> claimsJws = jwtParser.parseClaimsJws(jwt);
      claimsJws.getBody()
          .entrySet()
          .forEach(entry -> log.info("entry={}", entry));
    } catch (SignatureException e) {
      throw new IllegalArgumentException("Invalid JWT");
    } catch (ExpiredJwtException e) {
      throw new IllegalArgumentException("Expired JWT");
    } catch (Exception e) {
      throw new IllegalArgumentException("JWT parsing failed");
    }
  }

}
