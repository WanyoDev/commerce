package com.wanyoike.productservice.service;

import com.wanyoike.productservice.dto.*;

import java.util.List;

public interface ProductService {

    //PRODUCT
    ProductResponseDTO newProduct(ProductRequestDTO requestDTO);

    //CATEGORY
    CategoryDTO newCategory(CategoryRequestDTO requestDTO);

    //BRAND
    BrandDTO newBrand(BrandRequestDTO requestDTO);

    List<CategoryDTO> getAllCategories();

    List<BrandDTO> getAllBrands();

    List<ProductResponseDTO> getAllProducts();
}
