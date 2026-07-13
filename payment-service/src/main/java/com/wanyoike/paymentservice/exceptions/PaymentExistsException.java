package com.wanyoike.paymentservice.exceptions;

public class PaymentExistsException extends RuntimeException {
    public PaymentExistsException(String message) {
        super(message);
    }
}
