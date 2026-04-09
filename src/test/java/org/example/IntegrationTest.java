package org.example;

import org.example.math.Cos;
import org.example.math.Csc;
import org.example.math.Ln;
import org.example.math.Log10;
import org.example.math.Log2;
import org.example.math.Log3;
import org.example.math.Sec;
import org.example.math.Sin;
import org.example.math.Tan;
import org.example.stub.TableFunctionStub;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegrationTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldIntegrateBaseSinFirstFromCsv() {
        AbstractFunction sin = new Sin();

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/sin.csv")) {
            assertEquals(row.getDouble("expected"), sin.calculate(row.getDouble("x"), EPS), EPS);
        }
    }

    @Test
    void shouldIntegrateCosOverRealSinFromCsv() {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/cos.csv")) {
            assertEquals(row.getDouble("expected"), cos.calculate(row.getDouble("x"), EPS), EPS);
        }
    }

    @Test
    void shouldIntegrateTanOverRealSinAndCosFromCsv() {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction tan = new Tan(sin, cos);

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/tan.csv")) {
            double actual = tan.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }

    @Test
    void shouldIntegrateSecOverRealCosFromCsv() {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction sec = new Sec(cos);

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/sec.csv")) {
            double actual = sec.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }

    @Test
    void shouldIntegrateCscOverRealSinFromCsv() {
        AbstractFunction sin = new Sin();
        AbstractFunction csc = new Csc(sin);

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/csc.csv")) {
            double actual = csc.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }

    @Test
    void shouldIntegrateBaseLnFirstFromCsv() {
        AbstractFunction ln = new Ln();

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/ln.csv")) {
            double actual = ln.calculate(row.getDouble("x"), EPS);
            double expected = row.getDouble("expected");

            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual));
            } else {
                assertEquals(expected, actual, EPS);
            }
        }
    }

    @Test
    void shouldIntegrateLogarithmicModulesOverRealLnFromCsv() {
        AbstractFunction ln = new Ln();
        AbstractFunction log2 = new Log2(ln);
        AbstractFunction log3 = new Log3(ln);
        AbstractFunction log10 = new Log10(ln);

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/logarithms.csv")) {
            double actual = switch (row.getString("module")) {
                case "log2" -> log2.calculate(row.getDouble("x"), EPS);
                case "log3" -> log3.calculate(row.getDouble("x"), EPS);
                case "log10" -> log10.calculate(row.getDouble("x"), EPS);
                default -> throw new IllegalArgumentException("Unknown module");
            };

            assertEquals(row.getDouble("expected"), actual, EPS);
        }
    }

    @Test
    void shouldKeepSystemWorkingWhileReplacingModulesOneByOneFromCsv() {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction tan = new Tan(sin, cos);
        AbstractFunction sec = new Sec(cos);
        AbstractFunction csc = new Csc(sin);

        AbstractFunction ln = new Ln();
        AbstractFunction log2 = new Log2(ln);
        AbstractFunction log3 = new Log3(ln);
        AbstractFunction log10 = new Log10(ln);

        double logX = 8.0;
        Function trigStage = new Function(
                sin, cos, tan, sec, csc,
                new TableFunctionStub().add(logX, Math.log(logX)),
                new TableFunctionStub().add(logX, 3.0),
                new TableFunctionStub().add(logX, Math.log(logX) / Math.log(3.0)),
                new TableFunctionStub().add(logX, Math.log10(logX))
        );

        Function finalSystem = new Function(
                sin, cos, tan, sec, csc,
                ln, log2, log3, log10
        );

        for (CsvTestData.Row row : CsvTestData.load("testdata/integration/system.csv")) {
            String stage = row.getString("stage");
            double x = row.getDouble("x");
            String expected = row.getString("expected");

            if ("trig-stage".equals(stage)) {
                assertEquals(Double.parseDouble(expected), trigStage.calculate(x, EPS), EPS);
            } else if ("VALID".equals(expected)) {
                assertFalse(Double.isNaN(finalSystem.calculate(x, EPS)));
            } else {
                double actual = finalSystem.calculate(x, EPS);
                if ("NaN".equals(expected)) {
                    assertTrue(Double.isNaN(actual));
                } else {
                    assertEquals(Double.parseDouble(expected), actual, EPS);
                }
            }
        }
    }
}
