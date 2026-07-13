package com.wanyoike.paymentservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
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

    @ExceptionHandler(PaymentExistsException.class)
    public ResponseEntity<?> handlePaymentExistsException(PaymentExistsException ex) {

        log.info(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("payment", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<?> handlePaymentNotFoundException(PaymentNotFoundException ex) {

        log.info(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("payment", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
