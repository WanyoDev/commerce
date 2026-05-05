package com.wanyoike.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "brand", unique = true)
    private String brand;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private Set<Category> category;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    private LocalDateTime createdAt = LocalDateTime.now();
}
