package com.wanyoike.orderservice.service;

import com.wanyoike.orderservice.client.ProductClient;
import com.wanyoike.orderservice.client.ProductResponseDTO;
import com.wanyoike.orderservice.dto.OrderItemRequestDTO;
import com.wanyoike.orderservice.dto.OrderMapper;
import com.wanyoike.orderservice.dto.OrderRequestDTO;
import com.wanyoike.orderservice.dto.OrderResponseDTO;
import com.wanyoike.orderservice.exceptions.InsufficientStockException;
import com.wanyoike.orderservice.exceptions.OrderNotFoundException;
import com.wanyoike.orderservice.kafka.OrderEventProducer;
import com.wanyoike.orderservice.model.OrderItems;
import com.wanyoike.orderservice.model.Orders;
import com.wanyoike.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderEventProducer orderEventProducer;

    public OrderServiceImpl(ProductClient productClient, OrderRepository orderRepository, OrderMapper orderMapper, OrderEventProducer orderEventProducer) {
        this.productClient = productClient;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderEventProducer = orderEventProducer;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {

        //Set user id and email which are to be retrieved from user login
        Orders orders = new Orders();
        orders.setUserId(orderRequest.userId());
        orders.setEmail(orderRequest.email());
        orders.setBillingAddress(orderRequest.billingAddress());

        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderItems> items = new ArrayList<>();

        for (OrderItemRequestDTO orderItemRequest : orderRequest.items()) {

            ProductResponseDTO product = productClient.getProduct(orderItemRequest.productId());

            if(product.quantity() < orderItemRequest.quantity()) {

                throw new InsufficientStockException("Not enough stock for " + product.product());
            }

            BigDecimal subtotal = product.price()
                    .multiply(BigDecimal.valueOf(orderItemRequest.quantity()));

            //reserve inventory
            productClient.reduceStock(product.id(), orderItemRequest.quantity());

            OrderItems orderItems =
                    OrderItems.builder()
                            .productId(product.id())
                            .productName(product.product())
                            .unitPrice(product.price())
                            .quantity(orderItemRequest.quantity())
                            .subtotal(subtotal)
                            .orders(orders)
                            .build();

            items.add(orderItems);

            totalPrice = totalPrice.add(subtotal);
        }
        orders.setItems(items);
        orders.setTotalPrice(totalPrice);

        Orders saveOrder = orderRepository.save(orders);

        orderEventProducer.orderCreated(saveOrder);

        return orderMapper.toOrderResponseDTO(saveOrder);

    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orderMapper.toListOrderResponseDTO(orders);
    }

    @Override
    public List<OrderResponseDTO> findOrderByUserId(UUID userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toOrderResponseDTO)
                .toList();
    }

    @Override
    public OrderResponseDTO getOrderById(UUID orderId) {

        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order not found" +  orderId));

        return orderMapper.toOrderResponseDTO(orders);
    }

}
