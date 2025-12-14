package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserApiController {

  private final UserRepository userRepository;

  @GetMapping("/me")
  public UserDto me(
      HttpServletRequest httpServletRequest,
      @CookieValue("username") String username
  ) {
    /*Cookie[] cookies = httpServletRequest.getCookies();
    if (cookies != null) {
      Arrays.stream(cookies)
          .forEach(cookie ->
              log.info("name={}, value={}", cookie.getName(), cookie.getValue()));
    }*/

    log.info("username={}", username);

    return userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
  }

}
