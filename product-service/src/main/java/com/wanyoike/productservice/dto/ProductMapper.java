package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class, BrandMapper.class, ProductMapper.class})
public interface ProductMapper {

    //Entity to Response
    @Mapping(target="available", expression = "java(product.getQuantity() != null && product.getQuantity() > 0)")
    ProductResponseDTO toResponseDto(Product product);

    //Request to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductRequestDTO requestDTO);

}
