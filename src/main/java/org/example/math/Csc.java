package org.example.math;

public class Csc {

    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public double calculate(double x) {
        double sinVal = sin.calculate(x);
        if (Math.abs(sinVal) < 1e-10) return Double.NaN;

        return 1.0 / sinVal;
    }
}