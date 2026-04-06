package org.example.math;

import org.example.AbstractFunction;

public class Log2 implements AbstractFunction {

    private final AbstractFunction ln;

    public Log2(AbstractFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x, double eps) {
        return ln.calculate(x, eps) / ln.calculate(2, eps);
    }
}