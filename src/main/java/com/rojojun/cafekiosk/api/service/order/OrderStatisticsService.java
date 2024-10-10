package com.rojojun.cafekiosk.api.service.order;

import com.rojojun.cafekiosk.api.service.mail.MailService;
import com.rojojun.cafekiosk.domain.order.Order;
import com.rojojun.cafekiosk.domain.order.OrderRepository;
import com.rojojun.cafekiosk.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderStatisticsService {
    private final OrderRepository orderRepository;
    private final MailService mailService;

    public boolean sendOrderStatisticsMail(LocalDate orderDate, String email) {
        List<Order> orderList = orderRepository.findOrdersBy(
                orderDate.atStartOfDay(),
                orderDate.plusDays(1).atStartOfDay(),
                OrderStatus.COMPLETED
        );

        int totalAmount = orderList.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();

        boolean result = mailService.sendMail("no-reply@test.com",
                email,
                "[매출통계] %s".formatted(orderDate.toString()),
                "총 매출 합계는 %s원 입니다.".formatted(totalAmount));

        if (!result) {
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }

        return true;
    }
}
