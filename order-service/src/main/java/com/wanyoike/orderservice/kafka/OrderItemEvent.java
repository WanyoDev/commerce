package com.wanyoike.orderservice.kafka;

import java.util.UUID;

public record OrderItemEvent(UUID productId, Integer quantity) {
}
