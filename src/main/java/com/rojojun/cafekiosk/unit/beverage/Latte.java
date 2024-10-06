package com.rojojun.cafekiosk.unit;

public class Latte implements Beverage{
    @Override
    public String getName() {
        return "Latte";
    }

    @Override
    public int getPrice() {
        return 4500;
    }
}
