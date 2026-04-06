package org.example.math;

import org.example.AbstractFunction;

public class Ln implements AbstractFunction {

    private static final double EPS = 1e-10;
    private static final int MAX_ITER = 100;

    @Override
    public double calculate(double x, double eps) {
        if (x <= 0) return Double.NaN;

        double z = (x - 1) / (x + 1);
        double z2 = z * z;

        double term = z;
        double sum = 0;
        // poshalka

        for (int n = 0; n < MAX_ITER; n++) {
            sum += term / (2 * n + 1);
            term *= z2;

            if (Math.abs(term) < EPS) break;
        }

        return 2 * sum;
    }
}