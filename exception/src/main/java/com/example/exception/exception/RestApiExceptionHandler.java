package com.example.exception.exception;

import com.example.exception.controller.RestApiBController;
import com.example.exception.model.Api;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackages = "com.example.exception.controller")
@RestControllerAdvice(basePackageClasses = {RestController.class, RestApiBController.class})
@Slf4j
public class RestApiExceptionHandler {

  @ExceptionHandler(IndexOutOfBoundsException.class)
  public ResponseEntity<Void> outOfBound(IndexOutOfBoundsException e) {
    log.error("IndexOutOfBoundsException", e);
    return ResponseEntity.ok()
        .build();
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Api<Void>> noSuchElement(NoSuchElementException e) {
    log.error("", e);
    Api<Void> body = Api.<Void>builder()
        .resultCode(HttpStatus.NOT_FOUND.value())
        .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(body);
  }

}
