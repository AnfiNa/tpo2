package org.example.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Log10Test {

    private static final double EPS = 1e-6;

    private final Ln ln = new Ln();
    private final Log10 log10 = new Log10(ln);

    @Test
    void shouldCalculateLog10OfOne() {
        assertEquals(0.0, log10.calculate(1.0), EPS);
    }

    @Test
    void shouldCalculateLog10OfTen() {
        assertEquals(1.0, log10.calculate(10.0), EPS);
    }

    @Test
    void shouldCalculateLog10OfHundred() {
        assertEquals(2.0, log10.calculate(100.0), 0.01);
    }

    @Test
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(log10.calculate(-10.0)));
    }
}