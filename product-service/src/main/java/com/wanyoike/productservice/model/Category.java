package com.wanyoike.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false, name = "category", unique = true)
    private String category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_brand",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = {@JoinColumn(name = "brand_id")})
    private Set<Brand> brand;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
