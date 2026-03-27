package org.example.math;


import org.example.math.Sin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {

    private static final double EPS = 1e-6;
    private final Sin sin = new Sin();

    @Test
    void shouldCalculateZero() {
        assertEquals(0.0, sin.calculate(0.0), EPS);
    }

    @Test
    void shouldCalculatePiOverSix() {
        assertEquals(0.5, sin.calculate(Math.PI / 6), EPS);
    }

    @Test
    void shouldCalculateMinusPiOverSix() {
        assertEquals(-0.5, sin.calculate(-Math.PI / 6), EPS);
    }

    @Test
    void shouldCalculatePiOverTwo() {
        assertEquals(1.0, sin.calculate(Math.PI / 2), EPS);
    }

    @Test
    void shouldCalculateMinusPiOverTwo() {
        assertEquals(-1.0, sin.calculate(-Math.PI / 2), EPS);
    }

    @Test
    void shouldCalculatePi() {
        assertEquals(0.0, sin.calculate(Math.PI), EPS);
    }

    @Test
    void shouldWorkForLargeArgument() {
        double x = 100.0;
        assertEquals(Math.sin(x), sin.calculate(x), EPS);
    }
}
