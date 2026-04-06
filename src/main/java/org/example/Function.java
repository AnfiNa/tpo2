package org.example;

import org.example.math.*;

public class Function implements AbstractFunction {

    private final AbstractFunction sin;
    private final AbstractFunction cos;
    private final AbstractFunction tan;
    private final AbstractFunction sec;
    private final AbstractFunction csc;

    private final AbstractFunction ln;
    private final AbstractFunction log2;
    private final AbstractFunction log3;
    private final AbstractFunction log10;

    public Function(AbstractFunction sin, AbstractFunction cos, AbstractFunction tan, AbstractFunction sec, AbstractFunction csc,
                    AbstractFunction ln, AbstractFunction log2, AbstractFunction log3, AbstractFunction log10) {
        this.sin = sin;
        this.cos = cos;
        this.tan = tan;
        this.sec = sec;
        this.csc = csc;
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
        this.log10 = log10;
    }
    @Override
    public double calculate(double x, double eps) {

        if (x <= 0) {
            double secX = sec.calculate(x, eps);
            double sinX = sin.calculate(x, eps);
            double cosX = cos.calculate(x, eps);
            double tanX = tan.calculate(x, eps);
            double cscX = csc.calculate(x, eps);

            if (Double.isNaN(secX) || Double.isNaN(tanX) || Double.isNaN(cscX)) {
                return Double.NaN;
            }

            return (((secX - secX) + (secX * sinX)) * cosX - (sinX * cscX)) / tanX;

        } else {

            double log2X = log2.calculate(x, eps);
            double log3X = log3.calculate(x, eps);
            double log10X = log10.calculate(x, eps);
            double lnX = ln.calculate(x, eps);

            if (Math.abs(log3X) < eps) return Double.NaN;

            return (((log2X - log2X) + (log2X * log10X)) * log3X
                    - (log3X * lnX)) / log3X;
        }
    }
}
