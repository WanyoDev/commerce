package com.wanyoike.orderservice.kafka;

import com.wanyoike.orderservice.client.ProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventConsumer {

    private final ProductClient productClient;

    @KafkaListener(topics = "order-created",groupId = "product-group")
    public void consume(OrderEvent orderEvent) {

        for (OrderItemEvent item : orderEvent.items()) {
            productClient.reduceStock(item.productId(), item.quantity());
        }

        log.info("Order event processed successfully: {}", orderEvent);
    }

}
