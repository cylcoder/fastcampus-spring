package com.example.exception.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.OK;

@Order(1)
@RestControllerAdvice // Rest Controller 에서 발생하는 예외 감지
//@RestControllerAdvice(basePackages = "com.example.exception.controller") // 특정 패키지 지정
//@RestControllerAdvice(basePackageClasses = {RestApiControllerA.class, RestApiControllerB.class}) // 특정 클래스 지정
@Slf4j
public class RestApiExceptionHandler {

//    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> exception(Exception e) {
      log.error("RestApiExceptionHandler", e);

      return ResponseEntity.status(OK).build();
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<HttpStatus> indexOutOfBoundsException(IndexOutOfBoundsException e) {
        log.error("IndexOutOfBoundsException", e);

        return ResponseEntity.status(OK).build();
    }

}
