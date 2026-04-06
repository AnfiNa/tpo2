package org.example.math;

import org.example.AbstractFunction;

public class Tan implements AbstractFunction {

    private final AbstractFunction sin;
    private final AbstractFunction cos;

    public Tan(AbstractFunction sin, AbstractFunction cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double calculate(double x, double eps) {
        double cosVal = cos.calculate(x, eps);
        if (Math.abs(cosVal) < eps) return Double.NaN;

        return sin.calculate(x, eps) / cosVal;
    }
}
