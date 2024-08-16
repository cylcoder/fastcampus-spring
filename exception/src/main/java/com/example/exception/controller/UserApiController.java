package com.example.exception.controller;

import com.example.exception.model.Api;
import com.example.exception.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    private static List<UserResponse> users = List.of(
      UserResponse.builder().id("1").age(30).name("John").build(),
      UserResponse.builder().id("2").age(50).name("Jane").build()
    );

    @GetMapping("/id/{userId}")
    public Api<UserResponse> getUser(@PathVariable String userId) {
        UserResponse user = users.stream().filter(u -> u.getId().equals(userId)).findAny().orElseThrow();
        return Api.<UserResponse>builder()
                .resultCode(Integer.toString(OK.value()))
                .resultMessage(OK.name())
                .data(user).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Api> noSuchElementException(NoSuchElementException e) {
        log.error("NoSuchElementException", e);

        Api<Object> response = Api.builder()
                .resultCode(Integer.toString(NOT_FOUND.value()))
                .resultMessage(NOT_FOUND.name())
                .build();

        return ResponseEntity.status(NOT_FOUND)
                .body(response);
    }

}
