package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Brand;
import com.wanyoike.productservice.model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Enter product price")
    private BigDecimal price;

    @NotBlank(message = "Available quantity")
    private int quantity;

    private boolean available;

    private BrandDTO brandDTO;

    private CategoryDTO categoryDTO;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}
