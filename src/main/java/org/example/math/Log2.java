package org.example.math;

import org.example.AbstractFunction;

public class Log2 implements AbstractFunction {

    private final Ln ln;

    public Log2(Ln ln) {
        this.ln = ln;
    }

    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(2);
    }
}