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
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredTime) {
        List<String> productNumbers = request.getProductNumbers();

        List<Product> duplicateProducts = findProductsBy(productNumbers);

        Order order = Order.create(duplicateProducts, registeredTime);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        return productNumbers.stream()
                .map(productMap::get)
                .toList();
    }
}
