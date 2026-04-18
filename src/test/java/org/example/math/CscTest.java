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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CscTest {

    private static final double EPS = 1e-6;
    @Mock
    private AbstractFunction sinMock;

    @Test
    void shouldCalculateValuesFromCsv() {
        List<CsvTestData.Row> rows = CsvTestData.load("testdata/math/csc.csv");

        for (CsvTestData.Row row : rows) {
            when(sinMock.calculate(eq(row.getDouble("x")), eq(EPS))).thenReturn(row.getDouble("sinStub"));
        }

        Csc csc = new Csc(sinMock);

        for (CsvTestData.Row row : rows) {
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
