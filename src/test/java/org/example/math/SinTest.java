package org.example.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SinTest {

    private static final double EPS = 1e-6;

    private final Sin sin = new Sin();

    @Test
    void shouldCalculateZero() {
        assertEquals(0.0, sin.calculate(0.0, EPS), EPS);
    }

    @Test
    void shouldCalculatePiOverSix() {
        assertEquals(0.5, sin.calculate(Math.PI / 6, EPS), EPS);
    }

    @Test
    void shouldCalculateMinusPiOverTwo() {
        assertEquals(-1.0, sin.calculate(-Math.PI / 2, EPS), EPS);
    }

    @Test
    void shouldNormalizePeriod() {
        assertEquals(0.5, sin.calculate(13 * Math.PI / 6, EPS), EPS);
    }
}
