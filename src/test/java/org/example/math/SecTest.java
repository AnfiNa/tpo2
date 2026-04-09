package org.example.math;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateValuesFromCsv() {
        TableFunctionStub cosStub = new TableFunctionStub();

        for (CsvTestData.Row row : CsvTestData.load("testdata/math/sec.csv")) {
            cosStub.add(row.getDouble("x"), row.getDouble("cosStub"));
        }

        Sec sec = new Sec(cosStub);

        for (CsvTestData.Row row : CsvTestData.load("testdata/math/sec.csv")) {
            double actual = sec.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
