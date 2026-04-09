package org.example.math;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldDelegateToShiftedSinValue() {
        TableFunctionStub sinStub = new TableFunctionStub()
                .add(Math.PI / 2, 1.0)
                .add(Math.PI, 0.0);

        Cos cos = new Cos(sinStub);

        assertEquals(1.0, cos.calculate(0.0, EPS), EPS);
        assertEquals(0.0, cos.calculate(Math.PI / 2, EPS), EPS);
    }
}
