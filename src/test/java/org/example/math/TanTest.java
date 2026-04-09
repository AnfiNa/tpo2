package org.example.math;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TanTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateValuesFromCsv() {
        TableFunctionStub sinStub = new TableFunctionStub();
        TableFunctionStub cosStub = new TableFunctionStub();

        for (CsvTestData.Row row : CsvTestData.load("testdata/math/tan.csv")) {
            sinStub.add(row.getDouble("x"), row.getDouble("sinStub"));
            cosStub.add(row.getDouble("x"), row.getDouble("cosStub"));
        }

        Tan tan = new Tan(sinStub, cosStub);

        for (CsvTestData.Row row : CsvTestData.load("testdata/math/tan.csv")) {
            double actual = tan.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
