package org.example.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LnTest {

    private static final double EPS = 1e-6;

    private final Ln ln = new Ln();

    @Test
    void shouldReturnNaNOutsideDomain() {
        assertTrue(Double.isNaN(ln.calculate(0.0, EPS)));
        assertTrue(Double.isNaN(ln.calculate(-1.0, EPS)));
    }

    @Test
    void shouldCalculateOne() {
        assertEquals(0.0, ln.calculate(1.0, EPS), EPS);
    }

    @Test
    void shouldCalculateTwo() {
        assertEquals(Math.log(2.0), ln.calculate(2.0, EPS), EPS);
    }

    @Test
    void shouldCalculateFraction() {
        assertEquals(Math.log(0.5), ln.calculate(0.5, EPS), EPS);
    }
}
