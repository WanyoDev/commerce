package com.wanyoike.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Enter product name")
    @Column(name = "product", nullable = false)
    private String product;

    @NotBlank(message = "Enter product description")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Enter product price")
    @Column(name = "price", nullable = false)
    @Min(0)
    private BigDecimal price;

    @NotNull(message = "Available quantity")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    @Transient
//    public boolean isAvailable() {
//        return quantity != null && quantity > 0;
//    }
    //this won't be persisted to the database as it is derived from quantity
    //there should be a boolean available; datatype annotated with @Transient
}
