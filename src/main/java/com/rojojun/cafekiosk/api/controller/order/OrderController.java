package com.rojojun.cafekiosk.api.controller.order;

import com.rojojun.cafekiosk.api.service.order.OrderService;
import com.rojojun.cafekiosk.api.service.order.request.OrderCreateRequest;
import com.rojojun.cafekiosk.api.service.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request) {
        LocalDateTime registeredTime = LocalDateTime.now();
        return orderService.createOrder(request, registeredTime);
    }
}
