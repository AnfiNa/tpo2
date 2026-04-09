package org.example.math;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log10Test {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateValuesFromCsv() {
        var rows = CsvTestData.load("testdata/math/log10.csv");
        TableFunctionStub lnStub = new TableFunctionStub();

        for (CsvTestData.Row row : rows) {
            lnStub.add(row.getDouble("x"), row.getDouble("lnX"));
        }
        lnStub.add(10.0, rows.get(0).getDouble("lnBase"));

        Log10 log10 = new Log10(lnStub);

        for (CsvTestData.Row row : rows) {
            double actual = log10.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
