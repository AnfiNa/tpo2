package org.example.math;

import org.example.AbstractFunction;

public class Cos implements AbstractFunction {

    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double calculate(double x) {
        return sin.calculate(x + Math.PI / 2);
    }
}