package com.rojojun.cafekiosk.unit.order;

import com.rojojun.cafekiosk.domain.product.Product;
import com.rojojun.cafekiosk.unit.beverage.Beverage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Order {
    private final LocalDateTime orderDate;
    private final List<Beverage> beverages;
}
