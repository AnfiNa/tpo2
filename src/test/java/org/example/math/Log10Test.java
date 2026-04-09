package org.example.math;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log10Test {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateUsingLnStubTable() {
        TableFunctionStub lnStub = new TableFunctionStub()
                .add(100.0, 4.605170185988092)
                .add(10.0, 2.302585092994046);

        Log10 log10 = new Log10(lnStub);

        assertEquals(2.0, log10.calculate(100.0, EPS), EPS);
    }

    @Test
    void shouldPropagateNaNOutsideDomain() {
        TableFunctionStub lnStub = new TableFunctionStub()
                .add(-5.0, Double.NaN)
                .add(10.0, 2.302585092994046);

        Log10 log10 = new Log10(lnStub);

        assertTrue(Double.isNaN(log10.calculate(-5.0, EPS)));
    }
}
