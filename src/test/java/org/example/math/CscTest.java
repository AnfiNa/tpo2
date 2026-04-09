package org.example.math;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CscTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateInsideDomain() {
        TableFunctionStub sinStub = new TableFunctionStub()
                .add(Math.PI / 2, 1.0);

        Csc csc = new Csc(sinStub);

        assertEquals(1.0, csc.calculate(Math.PI / 2, EPS), EPS);
    }

    @Test
    void shouldReturnNaNWhenSinIsZero() {
        TableFunctionStub sinStub = new TableFunctionStub()
                .add(0.0, 0.0);

        Csc csc = new Csc(sinStub);

        assertTrue(Double.isNaN(csc.calculate(0.0, EPS)));
    }
}
