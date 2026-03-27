package org.example.math;

public class Cos {

    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double calculate(double x) {
        return sin.calculate(x + Math.PI / 2);
    }
}