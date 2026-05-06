package com.wanyoike.productservice.dto;

import com.wanyoike.productservice.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDTO toDto(Brand brand);
    
    Brand toEntity(BrandDTO brandDTO);

    List<Brand> toEntity(List<BrandDTO> brandDTOS);

    List<BrandDTO> toDto(List<Brand> brands);

}
