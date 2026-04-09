package org.example.math;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateValuesFromCsv() {
        TableFunctionStub sinStub = new TableFunctionStub()
                .add(Math.PI / 2, 1.0)
                .add(Math.PI, 0.0);

        Cos cos = new Cos(sinStub);

        for (CsvTestData.Row row : CsvTestData.load("testdata/math/cos.csv")) {
            assertEquals(row.getDouble("expected"), cos.calculate(row.getDouble("x"), EPS), EPS);
        }
    }
}
