package com.wanyoike.orderservice.dto;

import com.wanyoike.orderservice.model.Address;
import com.wanyoike.orderservice.model.OrderItems;
import com.wanyoike.orderservice.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
        UUID orderId,
        UUID userId,
        Address billingAddress,
        BigDecimal totalPrice,
        OrderStatus orderStatus,
        List<OrderItemRequestDTO> items,
        LocalDateTime createdAt
) {
}
