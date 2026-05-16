package com.wanyoike.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Choose category")
    @Column(nullable = false, name = "category", unique = true)
    private String category;

    @Column(nullable = false)
    boolean deleted = false;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Brand> brand;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
}
