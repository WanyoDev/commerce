package com.wanyoike.paymentservice.service;

import com.wanyoike.paymentservice.dto.PaymentResponseDto;
import com.wanyoike.paymentservice.kafka.OrderEvent;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    void processPayment(OrderEvent orderEvent);
    PaymentResponseDto getPayment(UUID paymentId);
    List<PaymentResponseDto> getPayments();
    List<PaymentResponseDto> getPaymentsByUserId(UUID userId);
}
