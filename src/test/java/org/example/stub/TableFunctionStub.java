package org.example.stub;

import org.example.AbstractFunction;

import java.util.ArrayList;
import java.util.List;

public class TableFunctionStub implements AbstractFunction {

    private static final double DEFAULT_MATCH_EPS = 1e-9;

    private final List<Entry> entries = new ArrayList<>();
    private final double matchEps;

    public TableFunctionStub() {
        this(DEFAULT_MATCH_EPS);
    }

    public TableFunctionStub(double matchEps) {
        this.matchEps = matchEps;
    }

    public TableFunctionStub add(double x, double value) {
        entries.add(new Entry(x, value));
        return this;
    }

    @Override
    public double calculate(double x, double eps) {
        for (Entry entry : entries) {
            if (Math.abs(entry.x - x) <= matchEps) {
                return entry.value;
            }
        }

        throw new IllegalArgumentException("No table value for x=" + x);
    }

    private record Entry(double x, double value) {
    }
}
