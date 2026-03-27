package org.example.math;

public class Log3 {

    private final Ln ln;

    public Log3(Ln ln) {
        this.ln = ln;
    }

    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(3);
    }
}
