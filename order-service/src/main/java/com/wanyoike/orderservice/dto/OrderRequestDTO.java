package com.wanyoike.orderservice.dto;

import com.wanyoike.orderservice.model.Address;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(
        UUID userId,
        String email,
        Address billingAddress,
        List<OrderItemRequestDTO> items
) {
}
