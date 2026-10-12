package com.ecommerce.order.application;

import com.ecommerce.order.application.dto.CreateOrderRequest;
import com.ecommerce.order.domain.Order;

public interface CreateOrderUseCase {

    Order execute(CreateOrderRequest request);

}