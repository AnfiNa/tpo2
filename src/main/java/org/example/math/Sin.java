package org.example.math;

import org.example.AbstractFunction;

public class Sin implements AbstractFunction {

    private static final double EPS = 1e-10;
    private static final int MAX_ITER = 100;

    public double calculate(double x) {
        // нормализация в [-pi, pi]
        x = normalize(x);

        double term = x;
        double sum = x;

        for (int i = 1; i < MAX_ITER; i++) {
            term *= -x * x / ((2 * i) * (2 * i + 1));
            sum += term;

            if (Math.abs(term) < EPS) break;
        }

        return sum;
    }

    private double normalize(double x) {
        double twoPi = 2 * Math.PI;
        x %= twoPi;
        if (x > Math.PI) x -= twoPi;
        if (x < -Math.PI) x += twoPi;
        return x;
    }
}