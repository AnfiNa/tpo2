package org.example.math;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateInsideDomain() {
        TableFunctionStub cosStub = new TableFunctionStub()
                .add(0.0, 1.0);

        Sec sec = new Sec(cosStub);

        assertEquals(1.0, sec.calculate(0.0, EPS), EPS);
    }

    @Test
    void shouldReturnNaNWhenCosIsZero() {
        TableFunctionStub cosStub = new TableFunctionStub()
                .add(Math.PI / 2, 0.0);

        Sec sec = new Sec(cosStub);

        assertTrue(Double.isNaN(sec.calculate(Math.PI / 2, EPS)));
    }
}
