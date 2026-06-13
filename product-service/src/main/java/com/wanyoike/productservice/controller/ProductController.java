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
@RequestMapping("/commerce")
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
        return new ResponseEntity<>(productService.getAllProductsByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/products/brand/{brandId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByBrandId(@PathVariable UUID brandId) {
        return new ResponseEntity<>(productService.getAllProductsByBrandId(brandId), HttpStatus.OK);
    }

    @DeleteMapping("/admin/product/delete/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("/products/{id}/stock")
    public ResponseEntity<Void> reduceStock(@PathVariable UUID id, @RequestParam int quantity){

        productService.reduceStock(id, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
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

    @DeleteMapping("/admin/category/delete/{categoryId}")
    public void deleteCategory(@PathVariable UUID categoryId) {
        productService.deleteCategory(categoryId);
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

    @DeleteMapping("/admin/brand/delete/{brandId}")
    public void deleteBrand(@PathVariable UUID brandId) {
        productService.deleteBrand(brandId);
    }
}
