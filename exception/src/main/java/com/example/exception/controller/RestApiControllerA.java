package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/a")
@Slf4j
public class RestApiControllerA {

    @GetMapping
    public void hello() {
        throw new IndexOutOfBoundsException();
    }

}
