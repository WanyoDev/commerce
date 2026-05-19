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

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException ex) {

        log.info(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("category", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<?> handleBrandNotFoundException(BrandNotFoundException ex) {

        log.info(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("brand", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {

        log.info(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("product", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
