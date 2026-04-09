package org.example;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvExporterTest {

    private static final double EPS = 1e-6;

    @Test
    void shouldExportNamedModuleToCsv() throws Exception {
        CsvExporter exporter = new CsvExporter();
        AbstractFunction function = (x, eps) -> x * x;
        Path file = Files.createTempFile("csv-exporter-", ".csv");

        try {
            exporter.export("square", function, 0.0, 0.2, 0.1, EPS, file.toString(), ";");

            assertEquals(
                    """
                    X;Result of square(X)
                    0.0000000000;0.0000000000
                    0.1000000000;0.0100000000
                    0.2000000000;0.0400000000
                    """.trim(),
                    Files.readString(file).replace("\r\n", "\n").trim()
            );
        } finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void shouldRejectNonPositiveStep() {
        CsvExporter exporter = new CsvExporter();

        assertThrows(
                IllegalArgumentException.class,
                () -> exporter.export("square", (x, eps) -> x, 0.0, 1.0, 0.0, EPS, "ignored.csv", ";")
        );
    }
}
