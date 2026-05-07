package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Brand;
import com.wanyoike.productservice.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CategoryDTO(UUID id,
                          String category,
                          LocalDateTime createdAt) {
}
