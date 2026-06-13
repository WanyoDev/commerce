package com.wanyoike.orderservice.client;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String product,
        BigDecimal price,
        int quantity
) {
}
