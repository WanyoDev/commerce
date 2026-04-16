package com.wanyoike.authenticationservice.dto;

import com.wanyoike.authenticationservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role", target = "role", defaultValue = "USER")
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> listToDto(List<User> users);
}
