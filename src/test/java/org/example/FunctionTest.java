package org.example;

import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FunctionTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateTrigonometricBranchFromCsv() {
        for (CsvTestData.Row row : CsvTestData.load("testdata/function/trigonometric-branch.csv")) {
            double x = row.getDouble("x");

            AbstractFunction sin = mock(AbstractFunction.class);
            AbstractFunction cos = mock(AbstractFunction.class);
            AbstractFunction tan = mock(AbstractFunction.class);
            AbstractFunction sec = mock(AbstractFunction.class);
            AbstractFunction csc = mock(AbstractFunction.class);

            when(sin.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("sin"));
            when(cos.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("cos"));
            when(tan.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("tan"));
            when(sec.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("sec"));
            when(csc.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("csc"));

            Function function = new Function(
                    sin,
                    cos,
                    tan,
                    sec,
                    csc,
                    mock(AbstractFunction.class),
                    mock(AbstractFunction.class),
                    mock(AbstractFunction.class),
                    mock(AbstractFunction.class)
            );

            double actual = function.calculate(x, EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }

    @Test
    void shouldCalculateLogarithmicBranchFromCsv() {
        for (CsvTestData.Row row : CsvTestData.load("testdata/function/logarithmic-branch.csv")) {
            double x = row.getDouble("x");

            AbstractFunction ln = mock(AbstractFunction.class);
            AbstractFunction log2 = mock(AbstractFunction.class);
            AbstractFunction log3 = mock(AbstractFunction.class);
            AbstractFunction log10 = mock(AbstractFunction.class);

            when(ln.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("ln"));
            when(log2.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("log2"));
            when(log3.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("log3"));
            when(log10.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("log10"));

            Function function = new Function(
                    mock(AbstractFunction.class),
                    mock(AbstractFunction.class),
                    mock(AbstractFunction.class),
                    mock(AbstractFunction.class),
                    mock(AbstractFunction.class),
                    ln,
                    log2,
                    log3,
                    log10
            );

            double actual = function.calculate(x, EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
