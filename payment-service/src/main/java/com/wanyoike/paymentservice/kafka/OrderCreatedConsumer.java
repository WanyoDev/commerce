package com.wanyoike.paymentservice.kafka;

import com.wanyoike.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "order-created",groupId = "payment-service")
    public void consume(OrderEvent orderEvent){
        paymentService.processPayment(orderEvent);
    }
}
