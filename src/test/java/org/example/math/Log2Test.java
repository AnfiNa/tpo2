package org.example.math;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log2Test {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateUsingLnStubTable() {
        TableFunctionStub lnStub = new TableFunctionStub()
                .add(8.0, 2.0794415416798357)
                .add(2.0, 0.6931471805599453);

        Log2 log2 = new Log2(lnStub);

        assertEquals(3.0, log2.calculate(8.0, EPS), EPS);
    }

    @Test
    void shouldPropagateNaNOutsideDomain() {
        TableFunctionStub lnStub = new TableFunctionStub()
                .add(-1.0, Double.NaN)
                .add(2.0, 0.6931471805599453);

        Log2 log2 = new Log2(lnStub);

        assertTrue(Double.isNaN(log2.calculate(-1.0, EPS)));
    }
}
