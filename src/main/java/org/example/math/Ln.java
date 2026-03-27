package org.example.math;

public class Ln {

    private static final double EPS = 1e-10;
    private static final int MAX_ITER = 100;

    public double calculate(double x) {
        if (x <= 0) return Double.NaN;

        double z = (x - 1) / (x + 1);
        double z2 = z * z;

        double term = z;
        double sum = 0;

        for (int n = 0; n < MAX_ITER; n++) {
            sum += term / (2 * n + 1);
            term *= z2;

            if (Math.abs(term) < EPS) break;
        }

        return 2 * sum;
    }
}