package org.example.math;

import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CosTest {

    private static final double EPS = 1e-6;
    @Spy
    private Sin sinSpy = new Sin();

    @Test
    void shouldCalculateValuesFromCsv() {
        List<CsvTestData.Row> rows = CsvTestData.load("testdata/math/cos.csv");
        Cos cos = new Cos(sinSpy);

        for (CsvTestData.Row row : rows) {
            assertEquals(row.getDouble("expected"), cos.calculate(row.getDouble("x"), EPS), EPS);
        }

        verify(sinSpy, times(rows.size())).calculate(anyDouble(), eq(EPS));
    }
}
