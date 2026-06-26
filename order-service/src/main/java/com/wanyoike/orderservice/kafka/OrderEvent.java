package com.wanyoike.orderservice.kafka;

import java.util.List;
import java.util.UUID;

public record OrderEvent(
        UUID orderId,
        UUID userId,
        List<OrderItemEvent> items) {
}
