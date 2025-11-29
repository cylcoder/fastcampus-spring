package com.example.exception.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class RestApiController {

  @GetMapping
  public void hello() {
    List<String> list = List.of("hello");
    String s = list.get(1);
    log.info("s={}", s);
  }

}
