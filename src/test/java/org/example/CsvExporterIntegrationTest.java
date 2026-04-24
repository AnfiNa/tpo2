package org.example;

import org.example.math.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvExporterIntegrationTest {

    private static final double EPS = 1e-6;

    @TempDir
    Path tempDir;

    @Test
    void shouldExportRealSinModule() throws Exception {
        CsvExporter exporter = new CsvExporter();
        AbstractFunction sin = new Sin();

        Path file = tempDir.resolve("sin.csv");
        exporter.export("sin", sin, 0.0, 0.2, 0.1, EPS, file.toString(), ";");

        List<String> lines = Files.readAllLines(file);
        assertEquals("X;Result of sin(X)", lines.get(0));

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";", -1);
            double x = Double.parseDouble(parts[0]);
            double actual = Double.parseDouble(parts[1]);
            assertEquals(Math.sin(x), actual, 1e-5);
        }
    }
}
