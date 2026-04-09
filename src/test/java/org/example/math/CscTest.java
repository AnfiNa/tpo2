package org.example.math;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CscTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateValuesFromCsv() {
        TableFunctionStub sinStub = new TableFunctionStub();

        for (CsvTestData.Row row : CsvTestData.load("testdata/math/csc.csv")) {
            sinStub.add(row.getDouble("x"), row.getDouble("sinStub"));
        }

        Csc csc = new Csc(sinStub);

        for (CsvTestData.Row row : CsvTestData.load("testdata/math/csc.csv")) {
            double actual = csc.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
