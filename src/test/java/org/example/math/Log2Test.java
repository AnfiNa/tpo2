package org.example.math;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log2Test {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateValuesFromCsv() {
        var rows = CsvTestData.load("testdata/math/log2.csv");
        TableFunctionStub lnStub = new TableFunctionStub();

        for (CsvTestData.Row row : rows) {
            lnStub.add(row.getDouble("x"), row.getDouble("lnX"));
        }
        lnStub.add(2.0, rows.get(0).getDouble("lnBase"));

        Log2 log2 = new Log2(lnStub);

        for (CsvTestData.Row row : rows) {
            double actual = log2.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
