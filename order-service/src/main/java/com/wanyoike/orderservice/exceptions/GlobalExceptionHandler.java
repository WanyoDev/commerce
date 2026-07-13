package com.wanyoike.orderservice.exceptions;

import com.wanyoike.orderservice.kafka.OrderEvent;
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

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotFoundException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach((fieldError) -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException ex) {

        log.error(ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        errors.put("orders", ex.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInsufficientStockException(InsufficientStockException ex) {

        log.info(ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        errors.put("stock", ex.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }
}
