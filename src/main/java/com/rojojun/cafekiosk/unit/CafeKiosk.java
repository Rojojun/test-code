package com.rojojun.cafekiosk.unit;

import com.rojojun.cafekiosk.unit.beverage.Beverage;
import com.rojojun.cafekiosk.unit.order.Order;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class CafeKiosk {
    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void add(Beverage beverage, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        }

        IntStream.range(0, count).forEach(i -> add(beverage));
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

//    public int calculateTotalPrice() {
//        return beverages.stream()
//                .mapToInt(Beverage::getPrice)
//                .sum();
//    }

    public int calculateTotalPrice() {
                return beverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }

    public Order createOrder(LocalDateTime currentDateTime) {
        LocalTime currentTime = currentDateTime.toLocalTime();

        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("time is outside the shop time");
        }

        return new Order(currentDateTime, beverages);
    }
}
