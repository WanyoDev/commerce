package com.wanyoike.productservice.service;

import com.wanyoike.productservice.dto.*;
import com.wanyoike.productservice.model.Brand;
import com.wanyoike.productservice.model.Category;
import com.wanyoike.productservice.model.Product;
import com.wanyoike.productservice.repository.BrandRepository;
import com.wanyoike.productservice.repository.CategoryRepository;
import com.wanyoike.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              BrandRepository brandRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDTO newProduct(ProductRequestDTO productRequestDTO) {

        Product product = productMapper.toEntity(productRequestDTO);

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(productRequestDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        product.setCategory(category);
        product.setBrand(brand);
        product.setCreatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);
        return productMapper.toResponseDto(saved);
    }
}
