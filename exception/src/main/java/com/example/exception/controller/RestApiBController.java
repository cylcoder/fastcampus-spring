package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/b")
@Slf4j
public class RestApiBController {

  @GetMapping("/hello")
  public void hello() {
    throw new NumberFormatException();
  }

  /*@ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<Void> numberFormatException(NumberFormatException e) {
    log.error("RestApiBController", e);
    return ResponseEntity.ok()
        .build();
  }*/

}