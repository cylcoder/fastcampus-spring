package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public String login(
      HttpServletResponse httpServletResponse,
      LoginRequest loginRequest
  ) {
    String username = loginRequest.username();
    String password = loginRequest.password();

    UserDto userDto = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

    if (!userDto.password().equals(password)) {
      throw new IllegalArgumentException("Invalid username or password");
    }

    return username;

/*    Cookie cookie = new Cookie("username", userDto.username());

    *//*
    * e.g. naver.com, daum.net, xxx.yyy.com
    * yyy.com으로 도메인을 설정한 경우 xxx.yyy.com, zzz.yyy.com 등에서 사용 가능
    * *//*
    cookie.setDomain("localhost");

    cookie.setPath("/");

    *//*
    * 음수인 경우 세션과 동일(종속) -> 브라우저를 닫는 순간(세션이 끝나는 순간) 쿠키는 삭제됨
    * 0인 경우 즉시 삭제
    * 양수인 경우 second 단위 e.g. 60(s) -> 1분
    * *//*
    cookie.setMaxAge(-1);

    cookie.setHttpOnly(true); // JS로 읽을 수 없도록 보안 처리

    cookie.setSecure(true); // HTTPS에서만 사용되도록 설정

    httpServletResponse.addCookie(cookie);*/
  }

}
