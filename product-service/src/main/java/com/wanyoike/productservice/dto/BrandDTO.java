package com.wanyoike.productservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BrandDTO(UUID id,
                       String brand,
                       LocalDateTime createdAt) {
}
