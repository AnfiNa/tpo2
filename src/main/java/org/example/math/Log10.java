package org.example.math;

public class Log10 {

    private final Ln ln;

    public Log10(Ln ln) {
        this.ln = ln;
    }

    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(10);
    }
}
