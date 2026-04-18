package org.example.math;

import org.example.AbstractFunction;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Log3Test {

    private static final double EPS = 1e-6;
    @Mock
    private AbstractFunction lnMock;

    @Test
    void shouldCalculateValuesFromCsv() {
        var rows = CsvTestData.load("testdata/math/log3.csv");

        for (CsvTestData.Row row : rows) {
            if (Double.compare(row.getDouble("x"), 3.0) != 0) {
                when(lnMock.calculate(eq(row.getDouble("x")), eq(EPS))).thenReturn(row.getDouble("lnX"));
            }
        }
        when(lnMock.calculate(eq(3.0), eq(EPS))).thenReturn(rows.get(0).getDouble("lnBase"));

        Log3 log3 = new Log3(lnMock);

        for (CsvTestData.Row row : rows) {
            double actual = log3.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
