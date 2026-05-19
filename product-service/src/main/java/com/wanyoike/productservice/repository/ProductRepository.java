package com.wanyoike.productservice.repository;

import com.wanyoike.productservice.model.Brand;
import com.wanyoike.productservice.model.Category;
import com.wanyoike.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategory(Category category);

    List<Product> findByBrand(Brand brand);
}
