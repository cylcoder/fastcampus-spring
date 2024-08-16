package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    @PostMapping
    public Api<UserRegisterRequest> register(
            @Valid @RequestBody Api<UserRegisterRequest> userRegisterRequest) {
        log.info("userRegisterRequest={}", userRegisterRequest);

        UserRegisterRequest data = userRegisterRequest.getData();

        return Api.<UserRegisterRequest>builder()
                .resultCode(String.valueOf(OK.value()))
                .resultMessage(OK.getReasonPhrase())
                .data(data)
                .build();
    }

}
