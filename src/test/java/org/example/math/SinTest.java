package org.example.math;

import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SinTest {

    private static final double EPS = 1e-6;

    private final Sin sin = new Sin();

    @Test
    void shouldCalculateValuesFromCsv() {
        for (CsvTestData.Row row : CsvTestData.load("testdata/math/sin.csv")) {
            assertEquals(row.getDouble("expected"), sin.calculate(row.getDouble("x"), EPS), EPS);
        }
    }
}
