package com.wanyoike.productservice.service;

import com.wanyoike.productservice.dto.*;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    //NEW PRODUCT, BRAND, CATEGORY
    ProductResponseDTO newProduct(ProductRequestDTO requestDTO);

    CategoryDTO newCategory(CategoryRequestDTO requestDTO);

    BrandDTO newBrand(BrandRequestDTO requestDTO);

    //LIST OF ALL PRODUCTS, BRANDS, CATEGORIES
    List<CategoryDTO> getAllCategories();

    List<BrandDTO> getAllBrands();

    List<ProductResponseDTO> getAllProducts();

    //SOFT DELETE PRODUCT, BRAND, CATEGORY
    void deleteProduct(UUID id);

    void deleteCategory(UUID id);

    void deleteBrand(UUID id);

    List<ProductResponseDTO> getAllProductsByCategoryId(UUID categoryId);

    List<ProductResponseDTO> getAllProductsByBrandId(UUID brandId);
}
