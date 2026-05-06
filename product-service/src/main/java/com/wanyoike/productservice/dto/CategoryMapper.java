package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> listToDto(List<Category> categories);

    List<Category> listToEntity(List<CategoryDTO> categoriesDTO);
}
