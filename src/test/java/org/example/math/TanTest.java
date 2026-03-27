package org.example.math;

import org.example.math.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TanTest {

    private static final double EPS = 1e-6;

    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Tan tan = new Tan(sin, cos);

    @Test
    void shouldCalculateTanPiOverFour() {
        assertEquals(1.0, tan.calculate(Math.PI / 4), EPS);
    }

    @Test
    void shouldCalculateTanMinusPiOverFour() {
        assertEquals(-1.0, tan.calculate(-Math.PI / 4), EPS);
    }

    @Test
    void shouldReturnNaNAtPiOverTwo() {
        assertTrue(Double.isNaN(tan.calculate(Math.PI / 2)));
    }

    @Test
    void shouldReturnNaNNearDiscontinuityIfExactPoint() {
        assertTrue(Double.isNaN(tan.calculate(-Math.PI / 2)));
    }
}