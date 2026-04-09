package org.example.math;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log3Test {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateValuesFromCsv() {
        var rows = CsvTestData.load("testdata/math/log3.csv");
        TableFunctionStub lnStub = new TableFunctionStub();

        for (CsvTestData.Row row : rows) {
            lnStub.add(row.getDouble("x"), row.getDouble("lnX"));
        }
        lnStub.add(3.0, rows.get(0).getDouble("lnBase"));

        Log3 log3 = new Log3(lnStub);

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
