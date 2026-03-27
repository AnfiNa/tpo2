package org.example.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LnTest {

    private static final double EPS = 1e-6;
    private final Ln ln = new Ln();

    @Test
    void shouldCalculateLnOfOne() {
        assertEquals(0.0, ln.calculate(1.0), EPS);
    }

    @Test
    void shouldCalculateLnOfTwo() {
        assertEquals(Math.log(2.0), ln.calculate(2.0), EPS);
    }

    @Test
    void shouldCalculateLnOfThree() {
        assertEquals(Math.log(3.0), ln.calculate(3.0), EPS);
    }

    @Test
    void shouldCalculateLnOfHalf() {
        assertEquals(Math.log(0.5), ln.calculate(0.5), EPS);
    }

    @Test
    void shouldReturnNaNForZero() {
        assertTrue(Double.isNaN(ln.calculate(0.0)));
    }

    @Test
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(ln.calculate(-2.0)));
    }
}