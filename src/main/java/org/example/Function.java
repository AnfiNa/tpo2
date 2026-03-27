package org.example;

import org.example.math.*;

public class Function implements AbstractFunction {

    private final Sin sin;
    private final Cos cos;
    private final Tan tan;
    private final Sec sec;
    private final Csc csc;

    private final Ln ln;
    private final Log2 log2;
    private final Log3 log3;
    private final Log10 log10;

    public Function(Sin sin, Cos cos, Tan tan, Sec sec, Csc csc,
                          Ln ln, Log2 log2, Log3 log3, Log10 log10) {
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
    public double calculate(double x) {

        if (x <= 0) {
            double secX = sec.calculate(x);
            double sinX = sin.calculate(x);
            double cosX = cos.calculate(x);
            double tanX = tan.calculate(x);
            double cscX = csc.calculate(x);

            if (Double.isNaN(secX) || Double.isNaN(tanX) || Double.isNaN(cscX)) {
                return Double.NaN;
            }

            return (((secX - secX) + (secX * sinX)) * cosX - (sinX * cscX)) / tanX;

        } else {

            double log2X = log2.calculate(x);
            double log3X = log3.calculate(x);
            double log10X = log10.calculate(x);
            double lnX = ln.calculate(x);

            if (Math.abs(log3X) < 1e-10) return Double.NaN;

            return (((log2X - log2X) + (log2X * log10X)) * log3X
                    - (log3X * lnX)) / log3X;
        }
    }
}
