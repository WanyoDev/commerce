package com.wanyoike.paymentservice.dto;

import com.wanyoike.paymentservice.model.Payment;
import com.wanyoike.paymentservice.model.PaymentMethod;

import java.util.UUID;

public record PaymentRequestDto(UUID orderId, PaymentMethod paymentMethod) {}
