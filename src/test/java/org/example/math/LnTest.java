package org.example.math;

import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LnTest {

    private static final double EPS = 1e-6;

    private final Ln ln = new Ln();

    @Test
    void shouldCalculateValuesFromCsv() {
        for (CsvTestData.Row row : CsvTestData.load("testdata/math/ln.csv")) {
            double actual = ln.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
