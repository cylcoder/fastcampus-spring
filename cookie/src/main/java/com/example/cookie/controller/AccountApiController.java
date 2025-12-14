package com.example.cookie.controller;

import com.example.cookie.model.LoginRequest;
import com.example.cookie.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountApiController {

  private final UserService userService;

  @PostMapping("/login")
  public void login(
      HttpServletResponse httpServletResponse,
      @RequestBody LoginRequest loginRequest
  ) {
    userService.login(httpServletResponse, loginRequest);
  }

}
