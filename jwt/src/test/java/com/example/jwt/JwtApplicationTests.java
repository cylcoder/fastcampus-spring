package com.example.jwt;

import com.example.jwt.service.JwtService;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtApplicationTests {

  @Autowired
  JwtService jwtService;

  @Test
  void contextLoads() {
  }

  @Test
  void jwt() {
    Map<String, Object> claims = Map.of(
        "username", "john",
        "gender", "male"
    );
    LocalDateTime localExpiredAt = LocalDateTime.now().plusMinutes(1);

    String jwt = jwtService.generateToken(claims, localExpiredAt);
    System.out.println("jwt = " + jwt);

    jwtService.validate(jwt);
  }

}
