package com.rojojun.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmericanoTest {
    @Test
    void getName() {
        Americano americano = new Americano();

        assertThat(americano.getName()).isEqualTo("Americano");
    }

    @Test
    void getPrice() {
        Americano americano = new Americano();

        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}