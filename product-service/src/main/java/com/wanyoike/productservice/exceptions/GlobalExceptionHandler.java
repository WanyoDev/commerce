package com.wanyoike.productservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> errors.put(error.getObjectName(), error.getDefaultMessage())
                );
        return ResponseEntity.badRequest().body(errors);
    }

//    @ExceptionHandler(UserEmailNotFoundException.class)
//    public ResponseEntity<?> handleUserEmailNotFoundException(UserEmailNotFoundException ex) {
//
//        log.info(ex.getMessage());
//
//        Map<String, String> errors = new HashMap<>();
//        errors.put("email", ex.getMessage());
//        return ResponseEntity.badRequest().body(errors);
//    }
}
