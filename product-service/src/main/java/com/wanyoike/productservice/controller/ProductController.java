package com.wanyoike.productservice.controller;

import com.wanyoike.productservice.dto.*;
import com.wanyoike.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String helloProduct() {
        return "hello product";
    }

    //ADMIN to add category and brand to ensure consistency
    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<>(productService.newProduct(productRequestDTO),  HttpStatus.CREATED);
    }

    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return new ResponseEntity<>(productService.newCategory(categoryRequestDTO),  HttpStatus.CREATED);
    }

    @PostMapping("/admin/brand")
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandRequestDTO brandRequestDTO) {
        return new ResponseEntity<>(productService.newBrand(brandRequestDTO),  HttpStatus.CREATED);
    }
}
