package com.example.exception.exception;

import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Api> exception(Exception e) {
        log.error("", e);

        Api<Object> response = Api.builder()
                .resultCode(Integer.toString(INTERNAL_SERVER_ERROR.value()))
                .resultMessage(INTERNAL_SERVER_ERROR.name())
                .build();

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(response);
    }

}
