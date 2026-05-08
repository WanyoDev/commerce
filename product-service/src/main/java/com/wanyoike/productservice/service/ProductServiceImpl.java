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
    private final BrandMapper brandMapper;
    private final CategoryMapper categoryMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              BrandRepository brandRepository, ProductMapper productMapper, BrandMapper brandMapper, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productMapper = productMapper;
        this.brandMapper = brandMapper;
        this.categoryMapper = categoryMapper;
    }

    //PRODUCT - CREATE, READ, UPDATE, DELETE
    @Override
    public ProductResponseDTO newProduct(ProductRequestDTO productRequestDTO) {

        Product product = productMapper.toProductEntity(productRequestDTO);

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(productRequestDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        product.setCategory(category);
        product.setBrand(brand);
        product.setCreatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);
        return productMapper.toProductResponseDto(saved);
    }

    //CATEGORY - CREATE, READ, UPDATE, DELETE
    @Override
    public CategoryDTO newCategory(CategoryRequestDTO requestDTO) {

        Category category = categoryMapper.toCategory(requestDTO);
        category.setCreatedAt(LocalDateTime.now());

        Category saved = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(saved);
    }

    //BRAND - CREATE, READ, UPDATE, DELETE
    @Override
    public BrandDTO newBrand(BrandRequestDTO requestDTO) {

        Brand brand = brandMapper.toBrand(requestDTO);
        brand.setCreatedAt(LocalDateTime.now());

        Brand saved = brandRepository.save(brand);
        return brandMapper.toBrandDTO(saved);
    }
}
