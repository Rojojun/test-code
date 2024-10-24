package com.rojojun.cafekiosk.domain.order;

import com.rojojun.cafekiosk.domain.product.Product;
import com.rojojun.cafekiosk.domain.product.ProductRepository;
import com.rojojun.cafekiosk.domain.product.ProductSellingStatus;
import com.rojojun.cafekiosk.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("특정 날짜와 타입을 받아 두 날짜 사이에 있는 주문 내역을 보여준다.")
    @Test
    void findOrdersBy() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 3, 5, 10, 0);

        Product product1 = createProduct("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "아메리카노", 1000);
        Product product2 = createProduct("002", ProductType.HANDMADE, ProductSellingStatus.HOLD, "카페라떼", 2000);
        Product product3 = createProduct("003", ProductType.HANDMADE, ProductSellingStatus.HOLD, "팥빙수", 3000);
        productRepository.saveAll(List.of(product1, product2, product3));

        List<Product> products = List.of(product1, product2, product3);
        Order order1 = createPaymentCompletedOrder(LocalDateTime.of(2023, 3, 4, 23, 59), products);
        Order order2 = createPaymentCompletedOrder(now, products);

        // when
        List<Order> orders = orderRepository.findOrdersBy(now, now.plusDays(2), OrderStatus.COMPLETED);
        System.out.println(orders);

        // then
        assertThat(orders).hasSize(1)
                .extracting("orderStatus", "totalPrice")
                .containsExactlyInAnyOrder(
                        tuple(OrderStatus.COMPLETED, 6000)
                );
    }

    private Order createPaymentCompletedOrder(LocalDateTime now, List<Product> products) {
        Order order = Order.builder()
                .products(products)
                .orderStatus(OrderStatus.COMPLETED)
                .registeredDateTime(now)
                .build();
        return orderRepository.save(order);
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}