package com.wanyoike.orderservice.repository;

import com.wanyoike.orderservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByUserId(UUID userId);
//    List<Orders> findByEmail(String email, Pageable pageable);
}
