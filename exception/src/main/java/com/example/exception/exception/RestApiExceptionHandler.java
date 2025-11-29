package com.example.exception.exception;

import com.example.exception.controller.RestApiBController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackages = "com.example.exception.controller")
@RestControllerAdvice(basePackageClasses = {RestController.class, RestApiBController.class})
@Slf4j
public class RestApiExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Void> exception(Exception e) {
    log.error("RestApiExceptionHandler", e);
    return ResponseEntity.ok()
        .build();
  }

  @ExceptionHandler(IndexOutOfBoundsException.class)
  public ResponseEntity<Void> outOfBound(IndexOutOfBoundsException e) {
    log.error("IndexOutOfBoundsException", e);
    return ResponseEntity.ok()
        .build();
  }

}
