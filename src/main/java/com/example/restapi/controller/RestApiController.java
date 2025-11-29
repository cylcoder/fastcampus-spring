package com.example.restapi.controller;

import com.example.restapi.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class RestApiController {

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/echo/{message}/age/{age}/is-man/{isMan}")
  public String echo(
      @PathVariable("message") String msg,
      @PathVariable int age,
      @PathVariable boolean isMan
  ) {
    System.out.println("msg = " + msg);
    System.out.println("age = " + age);
    System.out.println("isMan = " + isMan);
    return msg;
  }

  @GetMapping("/book")
  public void queryParam(
      String category,
      String issuedYear,
      @RequestParam("issued-month") String issuedMonth,
      @RequestParam("issued_Day") String issuedDay
  ) {
    System.out.println("category = " + category);
    System.out.println("issuedYear = " + issuedYear);
    System.out.println("issuedMonth = " + issuedMonth);
    System.out.println("issuedDay = " + issuedDay);
  }

  @GetMapping("/book2")
  public void queryParamDto(BookQueryParam bookQueryParam) {
    System.out.println("bookQueryParam = " + bookQueryParam);
  }

  @DeleteMapping(path = {
      "/user/{username}",
      "/user/{username}"
  })
  public void delete(@PathVariable String username) {
    log.info("username={}", username);
  }

}
