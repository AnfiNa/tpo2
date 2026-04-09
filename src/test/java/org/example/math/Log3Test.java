package org.example.math;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log3Test {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateUsingLnStubTable() {
        TableFunctionStub lnStub = new TableFunctionStub()
                .add(9.0, 2.1972245773362196)
                .add(3.0, 1.0986122886681098);

        Log3 log3 = new Log3(lnStub);

        assertEquals(2.0, log3.calculate(9.0, EPS), EPS);
    }

    @Test
    void shouldPropagateNaNOutsideDomain() {
        TableFunctionStub lnStub = new TableFunctionStub()
                .add(0.0, Double.NaN)
                .add(3.0, 1.0986122886681098);

        Log3 log3 = new Log3(lnStub);

        assertTrue(Double.isNaN(log3.calculate(0.0, EPS)));
    }
}
