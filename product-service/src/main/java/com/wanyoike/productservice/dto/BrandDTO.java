package com.wanyoike.productservice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record BrandDTO(UUID id, String brand, Set<CategoryDTO> categoryDTO,
                       List<ProductDTO> productDTO, LocalDateTime createdAt) {
}
