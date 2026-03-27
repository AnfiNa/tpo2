package org.example.math;

import org.example.AbstractFunction;

public class Tan implements AbstractFunction {

    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double calculate(double x) {
        double cosVal = cos.calculate(x);
        if (Math.abs(cosVal) < 1e-10) return Double.NaN;

        return sin.calculate(x) / cosVal;
    }
}
