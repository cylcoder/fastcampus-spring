package com.example.restapi.controller;

import com.example.restapi.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ResponseApiController {

  @GetMapping
  public ResponseEntity<UserRequest> user() {
    UserRequest userRequest = new UserRequest("john", 100, "john@example.com", true);
    return ResponseEntity.status(HttpStatus.CREATED)
        .header("X-Custom", "foo")
        .body(userRequest);
  }

}
