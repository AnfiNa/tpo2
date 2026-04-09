package org.example.math;

import org.example.AbstractFunction;

public class Ln implements AbstractFunction {

    private static final int MAX_ITER = 100;

    @Override
    public double calculate(double x, double eps) {
        if (x <= 0) return Double.NaN;

        double z = (x - 1) / (x + 1);
        double z2 = z * z;

        double term = z;
        double sum = 0;

        for (int n = 0; n < MAX_ITER; n++) {
            sum += term / (2 * n + 1);
            term *= z2;

            if (Math.abs(term) < eps) break;
        }

        return 2 * sum;
    }
}
