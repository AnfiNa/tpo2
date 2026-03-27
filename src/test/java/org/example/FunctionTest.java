package org.example;

import org.example.Function;
import org.example.math.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionTest {

    private static final double EPS = 1e-5;

    private Function system;

    @BeforeEach
    void setUp() {
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Tan tan = new Tan(sin, cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln ln = new Ln();
        Log2 log2 = new Log2(ln);
        Log3 log3 = new Log3(ln);
        Log10 log10 = new Log10(ln);

        system = new Function(sin, cos, tan, sec, csc, ln, log2, log3, log10);
    }

    @Test
    void shouldCalculateForNegativeXPiOverFour() {
        double x = -Math.PI / 4;

        double expected =
                (((((1 / Math.cos(x)) - (1 / Math.cos(x)))
                        + ((1 / Math.cos(x)) * Math.sin(x)))
                        * Math.cos(x))
                        - (Math.sin(x) * (1 / Math.sin(x))))
                        / Math.tan(x);

        double actual = system.calculate(x);

        Assertions.assertEquals(expected, actual, EPS);
    }

    @Test
    void shouldCalculateForNegativeXPiOverThree() {
        double x = -Math.PI / 3;

        double expected =
                (((((1 / Math.cos(x)) - (1 / Math.cos(x)))
                        + ((1 / Math.cos(x)) * Math.sin(x)))
                        * Math.cos(x))
                        - (Math.sin(x) * (1 / Math.sin(x))))
                        / Math.tan(x);

        double actual = system.calculate(x);

        Assertions.assertEquals(expected, actual, EPS);
    }

    @Test
    void shouldCalculateForPositiveXTwo() {
        double x = 2.0;

        double log2 = Math.log(x) / Math.log(2);
        double log3 = Math.log(x) / Math.log(3);
        double log10 = Math.log(x) / Math.log(10);
        double ln = Math.log(x);

        double expected =
                (((((log2 - log2) + (log2 * log10)) * log3)
                        - (log3 * ln)) / log3);

        double actual = system.calculate(x);

        Assertions.assertEquals(expected, actual, EPS);
    }

    @Test
    void shouldCalculateForPositiveXHalf() {
        double x = 0.5;

        double log2 = Math.log(x) / Math.log(2);
        double log3 = Math.log(x) / Math.log(3);
        double log10 = Math.log(x) / Math.log(10);
        double ln = Math.log(x);

        double expected =
                (((((log2 - log2) + (log2 * log10)) * log3)
                        - (log3 * ln)) / log3);

        double actual = system.calculate(x);

        Assertions.assertEquals(expected, actual, EPS);
    }

    @Test
    void shouldReturnNaNForZero() {
        Assertions.assertTrue(Double.isNaN(system.calculate(0.0)));
    }

    @Test
    void shouldReturnNaNForMinusPi() {
        Assertions.assertTrue(Double.isNaN(system.calculate(-Math.PI)));
    }

    @Test
    void shouldReturnNaNForMinusPiOverTwo() {
        Assertions.assertTrue(Double.isNaN(system.calculate(-Math.PI / 2)));
    }

    @Test
    void shouldReturnNaNForOne() {
        Assertions.assertTrue(Double.isNaN(system.calculate(1.0)));
    }

    @Test
    void shouldCalculateNearMinusPiButNotAtDiscontinuity() {
        double x = -Math.PI + 0.001;
        double actual = system.calculate(x);

        Assertions.assertFalse(Double.isNaN(actual));
        Assertions.assertFalse(Double.isInfinite(actual));
    }

    @Test
    void shouldCalculateNearMinusPiOverTwoButNotAtDiscontinuity() {
        double x = -Math.PI / 2 + 0.001;
        double actual = system.calculate(x);

        Assertions.assertFalse(Double.isNaN(actual));
        Assertions.assertFalse(Double.isInfinite(actual));
    }

    @Test
    void shouldCalculateNearOneFromLeft() {
        double x = 0.999;
        double actual = system.calculate(x);

        Assertions.assertFalse(Double.isNaN(actual));
        Assertions.assertFalse(Double.isInfinite(actual));
    }

    @Test
    void shouldCalculateNearOneFromRight() {
        double x = 1.001;
        double actual = system.calculate(x);

        Assertions.assertFalse(Double.isNaN(actual));
        Assertions.assertFalse(Double.isInfinite(actual));
    }

    @Test
    void shouldCalculateForLargePositiveX() {
        double x = 10.0;

        double log2 = Math.log(x) / Math.log(2);
        double log3 = Math.log(x) / Math.log(3);
        double log10 = Math.log(x) / Math.log(10);
        double ln = Math.log(x);

        double expected =
                (((((log2 - log2) + (log2 * log10)) * log3)
                        - (log3 * ln)) / log3);

        double actual = system.calculate(x);

        Assertions.assertEquals(expected, actual, EPS);
    }

    @Test
    void shouldCalculateForArbitraryNegativeX() {
        double x = -2.0;

        double expected =
                (((((1 / Math.cos(x)) - (1 / Math.cos(x)))
                        + ((1 / Math.cos(x)) * Math.sin(x)))
                        * Math.cos(x))
                        - (Math.sin(x) * (1 / Math.sin(x))))
                        / Math.tan(x);

        double actual = system.calculate(x);

        Assertions.assertEquals(expected, actual, EPS);
    }
}