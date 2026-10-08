package com.ecommerce.order.infrastructure;

// These imports fix the "Cannot resolve symbol" errors
import com.ecommerce.order.application.CreateOrderRequest;
import com.ecommerce.order.application.CreateOrderUseCase;
import com.ecommerce.order.domain.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Module", description = "Order management APIs")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    // Notice we inject the PORT (UseCase), not the Service implementation directly!
    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new order")
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return createOrderUseCase.execute(request);
    }
}