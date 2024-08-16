package com.example.validation.exception;

import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Api> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("", exception);

        List<String> errorMessages = exception
                .getFieldErrors()
                .stream()
                .map(error -> "%s: {%s} -> %s"
                        .formatted(error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .toList();

        var error = Api
                .Error
                .builder()
                .errorMessages(errorMessages)
                .build();

        Api<Object> body = Api
                .builder()
                .resultCode(String.valueOf(BAD_REQUEST.value()))
                .resultMessage(BAD_REQUEST.getReasonPhrase()).error(error).build();

        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

}
