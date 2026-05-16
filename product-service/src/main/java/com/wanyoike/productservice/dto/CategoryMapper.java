package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //Request to Response DTO
    CategoryDTO toCategoryDTO(Category category);

    @Mapping(target="id",  ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target="brand", ignore = true)
    Category toCategory(CategoryRequestDTO requestDTO);

    List<CategoryDTO> toCategoryListDTO(List<Category> categories);
}
