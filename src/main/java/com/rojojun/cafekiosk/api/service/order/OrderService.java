package com.rojojun.cafekiosk.api.service.order;

import com.rojojun.cafekiosk.api.service.order.request.OrderCreateRequest;
import com.rojojun.cafekiosk.api.service.order.response.OrderResponse;
import com.rojojun.cafekiosk.domain.order.Order;
import com.rojojun.cafekiosk.domain.order.OrderRepository;
import com.rojojun.cafekiosk.domain.product.Product;
import com.rojojun.cafekiosk.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredTime) {
        List<String> productNumbers = request.getProductNumbers();
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        Order order = Order.create(products, registeredTime);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }
}
