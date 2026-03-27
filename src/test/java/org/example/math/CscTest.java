package org.example.math;

import org.example.math.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CscTest {

    private static final double EPS = 1e-6;

    private final Sin sin = new Sin();
    private final Csc csc = new Csc(sin);

    @Test
    void shouldCalculateCscPiOverTwo() {
        assertEquals(1.0, csc.calculate(Math.PI / 2), EPS);
    }

    @Test
    void shouldCalculateCscMinusPiOverTwo() {
        assertEquals(-1.0, csc.calculate(-Math.PI / 2), EPS);
    }

    @Test
    void shouldReturnNaNAtZero() {
        assertTrue(Double.isNaN(csc.calculate(0.0)));
    }

    @Test
    void shouldReturnNaNAtPi() {
        assertTrue(Double.isNaN(csc.calculate(Math.PI)));
    }
}
