package org.example.math;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TanTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateInsideDomain() {
        TableFunctionStub sinStub = new TableFunctionStub()
                .add(Math.PI / 4, Math.sqrt(2) / 2);
        TableFunctionStub cosStub = new TableFunctionStub()
                .add(Math.PI / 4, Math.sqrt(2) / 2);

        Tan tan = new Tan(sinStub, cosStub);

        assertEquals(1.0, tan.calculate(Math.PI / 4, EPS), EPS);
    }

    @Test
    void shouldReturnNaNWhenCosIsZero() {
        TableFunctionStub sinStub = new TableFunctionStub()
                .add(Math.PI / 2, 1.0);
        TableFunctionStub cosStub = new TableFunctionStub()
                .add(Math.PI / 2, 0.0);

        Tan tan = new Tan(sinStub, cosStub);

        assertTrue(Double.isNaN(tan.calculate(Math.PI / 2, EPS)));
    }
}
