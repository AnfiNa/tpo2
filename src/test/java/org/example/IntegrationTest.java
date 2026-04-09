package org.example;

import org.example.math.Cos;
import org.example.math.Csc;
import org.example.math.Ln;
import org.example.math.Log10;
import org.example.math.Log2;
import org.example.math.Log3;
import org.example.math.Sec;
import org.example.math.Sin;
import org.example.math.Tan;
import org.example.stub.TableFunctionStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegrationTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldIntegrateBaseSinFirst() {
        AbstractFunction sin = new Sin();

        assertEquals(0.5, sin.calculate(Math.PI / 6, EPS), EPS);
        assertEquals(-1.0, sin.calculate(-Math.PI / 2, EPS), EPS);
    }

    @Test
    void shouldIntegrateCosOverRealSin() {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);

        assertEquals(1.0, cos.calculate(0.0, EPS), EPS);
        assertEquals(0.0, cos.calculate(Math.PI / 2, EPS), EPS);
    }

    @Test
    void shouldIntegrateTanOverRealSinAndCos() {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction tan = new Tan(sin, cos);

        assertEquals(1.0, tan.calculate(Math.PI / 4, EPS), EPS);
        assertTrue(Double.isNaN(tan.calculate(Math.PI / 2, EPS)));
    }

    @Test
    void shouldIntegrateSecOverRealCos() {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction sec = new Sec(cos);

        assertEquals(1.0, sec.calculate(0.0, EPS), EPS);
        assertTrue(Double.isNaN(sec.calculate(Math.PI / 2, EPS)));
    }

    @Test
    void shouldIntegrateCscOverRealSin() {
        AbstractFunction sin = new Sin();
        AbstractFunction csc = new Csc(sin);

        assertEquals(1.0, csc.calculate(Math.PI / 2, EPS), EPS);
        assertTrue(Double.isNaN(csc.calculate(0.0, EPS)));
    }

    @Test
    void shouldIntegrateBaseLnFirst() {
        AbstractFunction ln = new Ln();

        assertEquals(0.0, ln.calculate(1.0, EPS), EPS);
        assertEquals(Math.log(2.0), ln.calculate(2.0, EPS), EPS);
        assertTrue(Double.isNaN(ln.calculate(0.0, EPS)));
    }

    @Test
    void shouldIntegrateLogarithmicModulesOverRealLn() {
        AbstractFunction ln = new Ln();
        AbstractFunction log2 = new Log2(ln);
        AbstractFunction log3 = new Log3(ln);
        AbstractFunction log10 = new Log10(ln);

        assertEquals(3.0, log2.calculate(8.0, EPS), EPS);
        assertEquals(2.0, log3.calculate(9.0, EPS), EPS);
        assertEquals(1.0, log10.calculate(10.0, EPS), EPS);
    }

    @Test
    void shouldKeepSystemWorkingWhileReplacingModulesOneByOne() {
        double trigX = -Math.PI / 4;
        double logX = 8.0;

        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction tan = new Tan(sin, cos);
        AbstractFunction sec = new Sec(cos);
        AbstractFunction csc = new Csc(sin);

        AbstractFunction ln = new Ln();
        AbstractFunction log2 = new Log2(ln);
        AbstractFunction log3 = new Log3(ln);
        AbstractFunction log10 = new Log10(ln);

        Function trigStage = new Function(
                sin, cos, tan, sec, csc,
                new TableFunctionStub().add(logX, Math.log(logX)),
                new TableFunctionStub().add(logX, 3.0),
                new TableFunctionStub().add(logX, Math.log(logX) / Math.log(3.0)),
                new TableFunctionStub().add(logX, Math.log10(logX))
        );

        Function finalSystem = new Function(
                sin, cos, tan, sec, csc,
                ln, log2, log3, log10
        );

        assertEquals(1.7071067811865475, trigStage.calculate(trigX, EPS), EPS);
        assertEquals(0.629828419296, finalSystem.calculate(logX, EPS), EPS);
        assertTrue(Double.isNaN(finalSystem.calculate(1.0, EPS)));
        assertFalse(Double.isNaN(finalSystem.calculate(-2.0, EPS)));
    }
}
