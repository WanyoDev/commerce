package com.wanyoike.productservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryDTO(UUID id,
                          String category,
                          LocalDateTime createdAt) {
}
