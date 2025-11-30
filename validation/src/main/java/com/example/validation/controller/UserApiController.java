package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

  @PostMapping
  public Api<UserRegisterRequest> register(
      @RequestBody
      @Valid
      Api<UserRegisterRequest> req
  ) {
    log.info("req={}", req);
    UserRegisterRequest data = req.getData();
    return Api.<UserRegisterRequest>builder()
        .resultCode(HttpStatus.OK.value())
        .resultMessage(HttpStatus.OK.getReasonPhrase())
        .data(data)
        .build();
  }

}
