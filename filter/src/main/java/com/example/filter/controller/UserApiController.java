package com.example.filter.controller;

import com.example.filter.interceptor.OpenApi;
import com.example.filter.model.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserApiController {

  @PostMapping
  @OpenApi
  public void join(@RequestBody UserRequest userRequest) {
    log.info("userRequest={}", userRequest);
    throw new IllegalStateException();
  }

  @GetMapping
  public void notOpenApi() {
    log.info("UserApiController.notOpenApi()");
  }

}
