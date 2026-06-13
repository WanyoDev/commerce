package com.wanyoike.orderservice.dto;

import com.wanyoike.orderservice.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponseDTO toOrderResponseDTO(Orders order);

    Orders toOrders(OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTO> toListOrderResponseDTO(List<Orders> orders);
}
