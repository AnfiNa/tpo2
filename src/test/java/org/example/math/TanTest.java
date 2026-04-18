package org.example.math;

import org.example.AbstractFunction;
import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class TanTest {

    private static final double EPS = 1e-6;
    @Mock
    private AbstractFunction sinMock;
    @Mock
    private AbstractFunction cosMock;

    @Test
    void shouldCalculateValuesFromCsv() {
        List<CsvTestData.Row> rows = CsvTestData.load("testdata/math/tan.csv");

        for (CsvTestData.Row row : rows) {
            double x = row.getDouble("x");
            lenient().when(sinMock.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("sinStub"));
            lenient().when(cosMock.calculate(eq(x), eq(EPS))).thenReturn(row.getDouble("cosStub"));
        }

        Tan tan = new Tan(sinMock, cosMock);

        for (CsvTestData.Row row : rows) {
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
