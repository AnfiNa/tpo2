package org.example.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Log3Test {

    private static final double EPS = 1e-6;

    private final Ln ln = new Ln();
    private final Log3 log3 = new Log3(ln);

    @Test
    void shouldCalculateLog3OfOne() {
        assertEquals(0.0, log3.calculate(1.0), EPS);
    }

    @Test
    void shouldCalculateLog3OfThree() {
        assertEquals(1.0, log3.calculate(3.0), EPS);
    }

    @Test
    void shouldCalculateLog3OfNine() {
        assertEquals(2.0, log3.calculate(9.0), EPS);
    }

    @Test
    void shouldReturnNaNForZero() {
        assertTrue(Double.isNaN(log3.calculate(0.0)));
    }
}