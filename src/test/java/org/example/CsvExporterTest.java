package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CsvExporterTest {

    private static final double EPS = 1e-6;

    @TempDir
    Path tempDir;

    @Test
    void shouldExportHeaderAndRows() throws Exception {
        CsvExporter exporter = new CsvExporter();
        AbstractFunction module = mock(AbstractFunction.class);

        when(module.calculate(anyDouble(), anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0, Double.class);
            return x * 2;
        });

        Path file = tempDir.resolve("nested/module.csv");

        exporter.export("double", module, 0.0, 0.2, 0.1, EPS, file.toString(), ";");

        List<String> lines = Files.readAllLines(file);
        assertEquals("X;Result of double(X)", lines.get(0));
        assertEquals("0.0000000000;0.0000000000", lines.get(1));
        assertEquals("0.1000000000;0.2000000000", lines.get(2));
        assertEquals("0.2000000000;0.4000000000", lines.get(3));
    }

    @Test
    void shouldUseDefaultModuleNameInOverload() throws Exception {
        CsvExporter exporter = new CsvExporter();
        AbstractFunction module = mock(AbstractFunction.class);
        when(module.calculate(anyDouble(), anyDouble())).thenReturn(42.0);

        Path file = tempDir.resolve("default-name.csv");
        exporter.export(module, 1.0, 1.0, 0.1, EPS, file.toString(), ";");

        String header = Files.readAllLines(file).get(0);
        assertEquals("X;Result of module(X)", header);
    }

    @Test
    void shouldThrowWhenStepIsNotPositive() {
        CsvExporter exporter = new CsvExporter();
        AbstractFunction module = mock(AbstractFunction.class);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> exporter.export("m", module, 0.0, 1.0, 0.0, EPS, tempDir.resolve("a.csv").toString(), ";")
        );

        assertEquals("Step must be positive", exception.getMessage());
    }

    @Test
    void shouldThrowWhenStartGreaterThanEnd() {
        CsvExporter exporter = new CsvExporter();
        AbstractFunction module = mock(AbstractFunction.class);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> exporter.export("m", module, 2.0, 1.0, 0.1, EPS, tempDir.resolve("a.csv").toString(), ";")
        );

        assertEquals("Start must be less than or equal to end", exception.getMessage());
    }
}
