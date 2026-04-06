package org.example.math;

import org.example.AbstractFunction;

public class Cos implements AbstractFunction {

    private final AbstractFunction sin;

    public Cos(AbstractFunction sin) {
        this.sin = sin;
    }

    @Override
    public double calculate(double x, double eps) {
        return sin.calculate(x + Math.PI / 2, eps);
    }
}