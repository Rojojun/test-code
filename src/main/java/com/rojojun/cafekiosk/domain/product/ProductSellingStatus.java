package com.rojojun.cafekiosk.domain.product;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public enum ProductSellingStatus {
    SELLING("판매중"),
    HOLD("판매보류"),
    STOP_SELLING("판매 중지");

    private final String description;

    public static List<ProductSellingStatus> forDisplay() {
        return List.of(SELLING, HOLD);
    }
}
