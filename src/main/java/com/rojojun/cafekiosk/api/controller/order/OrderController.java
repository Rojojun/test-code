package com.rojojun.cafekiosk.api.controller.order;

import com.rojojun.cafekiosk.api.ApiResponse;
import com.rojojun.cafekiosk.api.controller.order.dto.request.OrderCreateRequest;
import com.rojojun.cafekiosk.api.service.order.OrderService;
import com.rojojun.cafekiosk.api.service.order.response.OrderResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {
    @NotEmpty(message = "상품 번호 주문 메시지는 필수입니다.")
    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        LocalDateTime registeredTime = LocalDateTime.now();
        return ApiResponse.ok(orderService.createOrder(request.toServiceRequest(), registeredTime));
    }
}
