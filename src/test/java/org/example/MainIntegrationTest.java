package org.example;

import org.example.testutil.CsvTestData;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainIntegrationTest {

    @Test
    void shouldGenerateAllCsvFilesFromMain() throws Exception {
        List<CsvTestData.Row> rows = CsvTestData.load("testdata/main/output-files.csv");

        for (CsvTestData.Row row : rows) {
            Files.deleteIfExists(Path.of(row.getString("path")));
        }

        Main.main(new String[0]);

        for (CsvTestData.Row row : rows) {
            Path file = Path.of(row.getString("path"));
            assertTrue(Files.exists(file), "File must exist: " + file);

            List<String> lines = Files.readAllLines(file);
            assertTrue(lines.size() > 1, "File must contain header and data: " + file);

            String moduleName = file.getFileName().toString().replace(".csv", "");
            assertEquals("X;Result of " + moduleName + "(X)", lines.get(0));
        }
    }
}
