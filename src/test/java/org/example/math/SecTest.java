package org.example.math;

import org.example.math.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecTest {

    private static final double EPS = 1e-6;

    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Sec sec = new Sec(cos);

    @Test
    void shouldCalculateSecZero() {
        assertEquals(1.0, sec.calculate(0.0), EPS);
    }

    @Test
    void shouldCalculateSecPiOverThree() {
        double x = Math.PI / 3;
        assertEquals(1.0 / Math.cos(x), sec.calculate(x), EPS);
    }

    @Test
    void shouldReturnNaNAtPiOverTwo() {
        assertTrue(Double.isNaN(sec.calculate(Math.PI / 2)));
    }
}