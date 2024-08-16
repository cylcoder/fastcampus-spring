package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/b")
@Slf4j
public class RestApiControllerB {

    @GetMapping
    public void hello() {
        throw new NumberFormatException();
    }

   /* @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<HttpStatus> numberFormatException(NumberFormatException e) {
        log.error("NumberFormatException", e);

        return ResponseEntity.ok().build();
    }*/

}
