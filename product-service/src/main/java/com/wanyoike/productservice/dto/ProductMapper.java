package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class, BrandMapper.class, ProductMapper.class})
public interface ProductMapper {

    //Entity to Response
    @Mapping(target="available", expression = "java(product.getQuantity() != null && product.getQuantity() > 0)")
    ProductResponseDTO toProductResponseDto(Product product);

    //Request to Entity
    //We are ignoring variables from product entity for the request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toProductEntity(ProductRequestDTO requestDTO);

    List<ProductResponseDTO> toListResponseDto(List<Product> products);
}
