package com.rojojun.cafekiosk.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    @DisplayName("특정 날짜와 타입을 받아 두 날짜 사이에 있는 주문 내역을 보여준다.")
    @Test
    void findOrdersBy() {
        // given

        // when

        // then

    }
}