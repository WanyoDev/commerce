package com.wanyoike.orderservice.repository;

import com.wanyoike.orderservice.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, UUID> {
}
