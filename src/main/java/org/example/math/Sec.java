package org.example.math;

import org.example.AbstractFunction;

public class Sec implements AbstractFunction {

    private final AbstractFunction cos;

    public Sec(AbstractFunction cos) {
        this.cos = cos;
    }

    @Override
    public double calculate(double x, double eps) {
        double cosVal = cos.calculate(x, eps);
        if (Math.abs(cosVal) < eps) return Double.NaN;

        return 1.0 / cosVal;
    }
}