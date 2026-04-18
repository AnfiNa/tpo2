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
class SecTest {

    private static final double EPS = 1e-6;
    @Mock
    private AbstractFunction cosMock;

    @Test
    void shouldCalculateValuesFromCsv() {
        List<CsvTestData.Row> rows = CsvTestData.load("testdata/math/sec.csv");

        for (CsvTestData.Row row : rows) {
            when(cosMock.calculate(eq(row.getDouble("x")), eq(EPS))).thenReturn(row.getDouble("cosStub"));
        }

        Sec sec = new Sec(cosMock);

        for (CsvTestData.Row row : rows) {
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
