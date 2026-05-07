package com.wanyoike.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private UUID id;

    @NotBlank(message = "Enter product name")
    private String product;

    @NotBlank(message = "Enter product description")
    private String description;

    @NotNull(message = "Enter product price")
    @DecimalMin(value = "0.0",  inclusive = false, message = "price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    private Boolean available; //derived as it fully depends on quantity

    private BrandDTO brandDTO;
    private CategoryDTO categoryDTO;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
