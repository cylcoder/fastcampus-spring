package com.example.exception.controller;

import com.example.exception.model.Api;
import com.example.exception.model.UserResponse;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

  private static List<UserResponse> users = List.of(
      UserResponse.builder()
          .id("1")
          .age(100)
          .name("john")
          .build(),
      UserResponse.builder()
          .id("2")
          .age(100)
          .name("jane")
          .build()
  );

  @GetMapping("/id/{userId}")
  public Api<UserResponse> getUser(@PathVariable String userId) {
    if ("ex".equals(userId)) {
      throw new IllegalStateException();
    }

    UserResponse user = users.stream()
        .filter(u -> u.getId().equals(userId))
        .findAny()
        .orElseThrow(NoSuchElementException::new);
    return Api.<UserResponse>builder()
        .resultCode(HttpStatus.OK.value())
        .resultMessage(HttpStatus.OK.getReasonPhrase())
        .data(user)
        .build();
  }

}
