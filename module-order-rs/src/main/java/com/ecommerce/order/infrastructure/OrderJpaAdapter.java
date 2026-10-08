package com.ecommerce.order.infrastructure;

import com.ecommerce.order.domain.Order;
import com.ecommerce.order.domain.OrderRepositoryPort;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class OrderJpaAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository springDataOrderRepository;

    // Constructor Injection
    public OrderJpaAdapter(SpringDataOrderRepository springDataOrderRepository) {
        this.springDataOrderRepository = springDataOrderRepository;
    }

    @Override
    public Order save(Order order) {
        // 1. Translate Domain to Entity
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setUserId(order.getUserId());
        entity.setProductId(order.getProductId());
        entity.setQuantity(order.getQuantity());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setStatus(order.getStatus());
        entity.setOrderDate(order.getOrderDate());

        // 2. Save to Database
        OrderEntity savedEntity = springDataOrderRepository.save(entity);

        // 3. Translate Entity back to Domain and return
        Order savedOrder = new Order();
        savedOrder.setId(savedEntity.getId());
        savedOrder.setUserId(savedEntity.getUserId());
        savedOrder.setProductId(savedEntity.getProductId());
        savedOrder.setQuantity(savedEntity.getQuantity());
        savedOrder.setTotalPrice(savedEntity.getTotalPrice());
        savedOrder.setStatus(savedEntity.getStatus());
        savedOrder.setOrderDate(savedEntity.getOrderDate());

        return savedOrder;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return springDataOrderRepository.findById(id).map(entity -> {
            // Translate Entity back to Domain
            Order order = new Order();
            order.setId(entity.getId());
            order.setUserId(entity.getUserId());
            order.setProductId(entity.getProductId());
            order.setQuantity(entity.getQuantity());
            order.setTotalPrice(entity.getTotalPrice());
            order.setStatus(entity.getStatus());
            order.setOrderDate(entity.getOrderDate());
            return order;
        });
    }
}