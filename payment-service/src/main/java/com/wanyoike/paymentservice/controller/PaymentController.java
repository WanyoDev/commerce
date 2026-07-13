package com.wanyoike.paymentservice.controller;

import com.wanyoike.paymentservice.dto.PaymentResponseDto;
import com.wanyoike.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable UUID paymentId) {

        return new ResponseEntity<>(paymentService.getPayment(paymentId), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getPayments(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDto>> getAllPaymentsByUserId(@PathVariable UUID userId) {
        return new ResponseEntity<>(paymentService.getPaymentsByUserId(userId), HttpStatus.OK);
    }
}
