package com.wanyoike.productservice.service;

import com.wanyoike.productservice.dto.*;

public interface ProductService {

    //PRODUCT
    ProductResponseDTO newProduct(ProductRequestDTO requestDTO);

    //CATEGORY
    CategoryDTO newCategory(CategoryRequestDTO requestDTO);

    //BRAND
    BrandDTO newBrand(BrandRequestDTO requestDTO);
}
