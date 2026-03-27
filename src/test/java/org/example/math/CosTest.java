package org.example.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest {

    private static final double EPS = 1e-6;

    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);

    @Test
    void shouldCalculateCosZero() {
        assertEquals(1.0, cos.calculate(0.0), EPS);
    }

    @Test
    void shouldCalculateCosPiOverThree() {
        assertEquals(Math.cos(Math.PI / 3), cos.calculate(Math.PI / 3), EPS);
    }

    @Test
    void shouldCalculateCosMinusPiOverThree() {
        assertEquals(Math.cos(-Math.PI / 3), cos.calculate(-Math.PI / 3), EPS);
    }

    @Test
    void shouldCalculateCosPi() {
        assertEquals(Math.cos(Math.PI), cos.calculate(Math.PI), EPS);
    }
}