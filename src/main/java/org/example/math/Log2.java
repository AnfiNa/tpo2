package org.example.math;

public class Log2 {

    private final Ln ln;

    public Log2(Ln ln) {
        this.ln = ln;
    }

    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(2);
    }
}