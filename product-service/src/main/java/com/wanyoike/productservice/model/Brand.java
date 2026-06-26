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

@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Select brand")
    @Column(nullable = false, name = "brand", unique = true)
    private String brand;

    @Column(nullable = false)
    private boolean deleted = false;

    //PERSIST & MERGE;don't remove a Category only because a Brand is deleted
    //Should be the owning side
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "brand_category",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> category;

    @OneToMany(mappedBy = "brand", cascade=CascadeType.ALL)
    private List<Product> products;

    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    //Category and brand will be matched to each other
    public void addCategory(Category category) {

        if (!this.category.contains(category)) {
            this.category.add(category);

            if(!category.getBrand().contains(this)) {
                category.getBrand().add(this);
            }
        }


    }
}
