package com.wanyoike.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/commerce/products/{id}")
    ProductResponseDTO getProduct(@PathVariable UUID id);

    @PutMapping("/commerce/products/{id}/stock")
    void reduceStock(@PathVariable UUID id, @RequestParam int quantity);
}
