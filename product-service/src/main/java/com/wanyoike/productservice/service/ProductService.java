package com.wanyoike.productservice.service;

import com.wanyoike.productservice.dto.ProductRequestDTO;
import com.wanyoike.productservice.dto.ProductResponseDTO;

public interface ProductService {

    ProductResponseDTO newProduct(ProductRequestDTO requestDTO);
}
