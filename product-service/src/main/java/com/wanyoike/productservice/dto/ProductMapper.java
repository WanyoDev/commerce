package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDto(Product product);

    Product toEntity(ProductDTO productDTO);

    List<ProductDTO> listToDto(List<Product> products);

    List<Product> listToEntity(List<ProductDTO> productDTOs);
}
