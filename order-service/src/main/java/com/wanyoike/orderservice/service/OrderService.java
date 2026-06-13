package com.wanyoike.orderservice.service;

import com.wanyoike.orderservice.dto.OrderRequestDTO;
import com.wanyoike.orderservice.dto.OrderResponseDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    List<OrderResponseDTO> getAllOrders();
    List<OrderResponseDTO> findOrderByUserId(UUID userId);
    OrderResponseDTO getOrderById(UUID orderId);
    //Order by Status
}
