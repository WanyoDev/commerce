package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Brand;
import com.wanyoike.productservice.model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductDTO {

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

    private boolean available;

    private BrandDTO brandDTO;

    private CategoryDTO categoryDTO;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
