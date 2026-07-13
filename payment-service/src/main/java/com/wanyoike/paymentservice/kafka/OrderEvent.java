package com.wanyoike.paymentservice.kafka;

import com.wanyoike.paymentservice.model.PaymentMethod;
import com.wanyoike.paymentservice.model.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderEvent(
        UUID orderId,
        UUID userId,
        PaymentStatus paymentStatus,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String paymentReference) {
}
