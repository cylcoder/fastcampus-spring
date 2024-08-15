package com.example.restapi.controller;

import com.example.restapi.model.BookRequest;
import com.example.restapi.model.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostApiController {

    // @RequestBody: POST 또는 PUT 방식에서 HTTP Body로 들어오는 값을 받음
    @PostMapping("/book")
    public BookRequest book(@RequestBody BookRequest bookRequest) {
        System.out.println("bookRequest = " + bookRequest);
        return bookRequest;
    }

    @PostMapping("/user")
    public UserRequest user(@RequestBody UserRequest userRequest) {
        System.out.println("userRequest = " + userRequest);
        return userRequest;
    }


}
