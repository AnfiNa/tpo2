package org.example.math;

import org.example.AbstractFunction;

public class Csc implements AbstractFunction {

    private final AbstractFunction sin;

    public Csc(AbstractFunction sin) {
        this.sin = sin;
    }

    @Override
    public double calculate(double x, double eps) {
        double sinVal = sin.calculate(x, eps);
        if (Math.abs(sinVal) < eps) return Double.NaN;

        return 1.0 / sinVal;
    }
}