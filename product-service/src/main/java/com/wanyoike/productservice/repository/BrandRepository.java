package com.wanyoike.productservice.repository;

import com.wanyoike.productservice.model.Brand;
import com.wanyoike.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    List<Brand> findByCategory(Category category);
}
