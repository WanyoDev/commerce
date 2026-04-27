package com.wanyoike.authenticationservice.dtos;

import com.wanyoike.authenticationservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);

    @Mapping(target = "password", ignore = true)
    List<UserDTO> listToDto(List<User> users);
}
