package com.example.validation.exception;

import com.example.validation.model.Api;
import com.example.validation.model.Api.Error;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Api<Void>> validationException(
      MethodArgumentNotValidException ex
  ) {
    log.error("", ex); // ch 3-8 validation 2 부터

    List<String> errorMessages = ex.getFieldErrors()
        .stream()
        .map(fieldError -> "field=%s, rejectedValue=%s, defaultMessage=%s".formatted(
            fieldError.getField(),
            fieldError.getRejectedValue(),
            fieldError.getDefaultMessage()
        )).toList();

    Error error = Error
        .builder()
        .errorMessages(errorMessages)
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            Api.<Void>builder()
            .resultCode(HttpStatus.BAD_REQUEST.value())
            .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .error(error)
            .build()
        );
  }

}
