package com.wanyoike.productservice.controller;

import com.wanyoike.productservice.dto.*;
import com.wanyoike.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //PRODUCT
    @PostMapping("/admin/product")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<>(productService.newProduct(productRequestDTO),  HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByCategoryId(@PathVariable UUID categoryId) {
        return new ResponseEntity<>(productService.getAllProductsByCategoryId(categoryId), HttpStatus.FOUND);
    }

    @GetMapping("/products/brand/{brandId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByBrandId(@PathVariable UUID brandId) {
        return new ResponseEntity<>(productService.getAllProductsByBrandId(brandId), HttpStatus.FOUND);
    }

    //CATEGORY
    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return new ResponseEntity<>(productService.newCategory(categoryRequestDTO),  HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(productService.getAllCategories(), HttpStatus.OK);
    }

    //BRAND
    @PostMapping("/admin/brand")
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandRequestDTO brandRequestDTO) {
        return new ResponseEntity<>(productService.newBrand(brandRequestDTO),  HttpStatus.CREATED);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        return new ResponseEntity<>(productService.getAllBrands(), HttpStatus.OK);
    }
}
