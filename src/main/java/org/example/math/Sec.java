package org.example.math;

public class Sec {

    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public double calculate(double x) {
        double cosVal = cos.calculate(x);
        if (Math.abs(cosVal) < 1e-10) return Double.NaN;

        return 1.0 / cosVal;
    }
}