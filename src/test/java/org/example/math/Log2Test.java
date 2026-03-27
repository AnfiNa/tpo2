package org.example.math;

import org.example.math.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Log2Test {

    private static final double EPS = 1e-6;

    private final Ln ln = new Ln();
    private final Log2 log2 = new Log2(ln);

    @Test
    void shouldCalculateLog2OfOne() {
        assertEquals(0.0, log2.calculate(1.0), EPS);
    }

    @Test
    void shouldCalculateLog2OfTwo() {
        assertEquals(1.0, log2.calculate(2.0), EPS);
    }

    @Test
    void shouldCalculateLog2OfEight() {
        assertEquals(3.0, log2.calculate(8.0), EPS);
    }

    @Test
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(log2.calculate(-1.0)));
    }
}