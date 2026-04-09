package org.example;

import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldCalculateTrigonometricBranchWithTableStubs() {
        double x = -Math.PI / 4;

        TableFunctionStub sinStub = new TableFunctionStub().add(x, -Math.sqrt(2) / 2);
        TableFunctionStub cosStub = new TableFunctionStub().add(x, Math.sqrt(2) / 2);
        TableFunctionStub tanStub = new TableFunctionStub().add(x, -1.0);
        TableFunctionStub secStub = new TableFunctionStub().add(x, Math.sqrt(2));
        TableFunctionStub cscStub = new TableFunctionStub().add(x, -Math.sqrt(2));

        TableFunctionStub lnStub = new TableFunctionStub();
        TableFunctionStub log2Stub = new TableFunctionStub();
        TableFunctionStub log3Stub = new TableFunctionStub();
        TableFunctionStub log10Stub = new TableFunctionStub();

        Function function = new Function(
                sinStub, cosStub, tanStub, secStub, cscStub,
                lnStub, log2Stub, log3Stub, log10Stub
        );

        assertEquals(1.7071067811865475, function.calculate(x, EPS), EPS);
    }

    @Test
    void shouldReturnNaNForTrigonometricDiscontinuityPoint() {
        double x = 0.0;

        TableFunctionStub sinStub = new TableFunctionStub().add(x, 0.0);
        TableFunctionStub cosStub = new TableFunctionStub().add(x, 1.0);
        TableFunctionStub tanStub = new TableFunctionStub().add(x, 0.0);
        TableFunctionStub secStub = new TableFunctionStub().add(x, 1.0);
        TableFunctionStub cscStub = new TableFunctionStub().add(x, Double.NaN);

        Function function = new Function(
                sinStub, cosStub, tanStub, secStub, cscStub,
                new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub()
        );

        assertTrue(Double.isNaN(function.calculate(x, EPS)));
    }

    @Test
    void shouldReturnNaNForCosDependentTrigonometricPoint() {
        double x = -Math.PI / 2;

        TableFunctionStub sinStub = new TableFunctionStub().add(x, -1.0);
        TableFunctionStub cosStub = new TableFunctionStub().add(x, 0.0);
        TableFunctionStub tanStub = new TableFunctionStub().add(x, Double.NaN);
        TableFunctionStub secStub = new TableFunctionStub().add(x, Double.NaN);
        TableFunctionStub cscStub = new TableFunctionStub().add(x, -1.0);

        Function function = new Function(
                sinStub, cosStub, tanStub, secStub, cscStub,
                new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub()
        );

        assertTrue(Double.isNaN(function.calculate(x, EPS)));
    }

    @Test
    void shouldCalculateLogarithmicBranchWithTableStubs() {
        double x = 8.0;

        TableFunctionStub lnStub = new TableFunctionStub().add(x, 2.0794415416798357);
        TableFunctionStub log2Stub = new TableFunctionStub().add(x, 3.0);
        TableFunctionStub log3Stub = new TableFunctionStub().add(x, 1.8927892607143721);
        TableFunctionStub log10Stub = new TableFunctionStub().add(x, 0.9030899869919435);

        Function function = new Function(
                new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(),
                lnStub, log2Stub, log3Stub, log10Stub
        );

        assertEquals(0.629828419296, function.calculate(x, EPS), EPS);
    }

    @Test
    void shouldReturnNaNAtLogarithmicDependencyPoint() {
        double x = 1.0;

        TableFunctionStub lnStub = new TableFunctionStub().add(x, 0.0);
        TableFunctionStub log2Stub = new TableFunctionStub().add(x, 0.0);
        TableFunctionStub log3Stub = new TableFunctionStub().add(x, 0.0);
        TableFunctionStub log10Stub = new TableFunctionStub().add(x, 0.0);

        Function function = new Function(
                new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(), new TableFunctionStub(),
                lnStub, log2Stub, log3Stub, log10Stub
        );

        assertTrue(Double.isNaN(function.calculate(x, EPS)));
    }
}
