package com.wanyoike.orderservice.kafka;

import com.wanyoike.orderservice.model.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void orderCreated(Orders order){

        OrderEvent event =
                new OrderEvent(
                        order.getOrderId(),
                        order.getUserId(),
                        order.getItems()
                                .stream()
                                .map(i -> new OrderItemEvent(i.getProductId(), i.getQuantity()))
                                .toList());

        kafkaTemplate.send("order-created", event);
    }
}
