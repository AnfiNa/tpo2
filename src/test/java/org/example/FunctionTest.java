package org.example;

import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateTrigonometricBranchFromCsv() {
        for (CsvTestData.Row row : CsvTestData.load("testdata/function/trigonometric-branch.csv")) {
            double x = row.getDouble("x");

            Function function = new Function(
                    new TableFunctionStub().add(x, row.getDouble("sin")),
                    new TableFunctionStub().add(x, row.getDouble("cos")),
                    new TableFunctionStub().add(x, row.getDouble("tan")),
                    new TableFunctionStub().add(x, row.getDouble("sec")),
                    new TableFunctionStub().add(x, row.getDouble("csc")),
                    new TableFunctionStub(),
                    new TableFunctionStub(),
                    new TableFunctionStub(),
                    new TableFunctionStub()
            );

            double actual = function.calculate(x, EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }

    @Test
    void shouldCalculateLogarithmicBranchFromCsv() {
        for (CsvTestData.Row row : CsvTestData.load("testdata/function/logarithmic-branch.csv")) {
            double x = row.getDouble("x");

            Function function = new Function(
                    new TableFunctionStub(),
                    new TableFunctionStub(),
                    new TableFunctionStub(),
                    new TableFunctionStub(),
                    new TableFunctionStub(),
                    new TableFunctionStub().add(x, row.getDouble("ln")),
                    new TableFunctionStub().add(x, row.getDouble("log2")),
                    new TableFunctionStub().add(x, row.getDouble("log3")),
                    new TableFunctionStub().add(x, row.getDouble("log10"))
            );

            double actual = function.calculate(x, EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }
}
