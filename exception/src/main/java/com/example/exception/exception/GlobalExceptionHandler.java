package com.example.exception.exception;

import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Api<Void>> exception(Exception e) {
    log.error("RestApiExceptionHandler", e);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Api.<Void>builder()
            .resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .build()
        );
  }

}
