package org.example.math;

public class Tan {

    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public double calculate(double x) {
        double cosVal = cos.calculate(x);
        if (Math.abs(cosVal) < 1e-10) return Double.NaN;

        return sin.calculate(x) / cosVal;
    }
}
