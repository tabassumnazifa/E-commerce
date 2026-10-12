package com.ecommerce.order.domain.out;

import com.ecommerce.order.domain.Order;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
}