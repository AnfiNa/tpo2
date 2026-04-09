package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void shouldGenerateCsvFilesForAllModules() throws Exception {
        String[] files = {
                "csv-output/sin.csv", "csv-output/cos.csv", "csv-output/tan.csv", "csv-output/sec.csv", "csv-output/csc.csv",
                "csv-output/ln.csv", "csv-output/log2.csv", "csv-output/log3.csv", "csv-output/log10.csv", "csv-output/system.csv"
        };
        Map<String, byte[]> backups = backupFiles(files);

        try {
            Main.main(new String[0]);

            for (String file : files) {
                Path path = Path.of(file);
                assertTrue(Files.exists(path));
                assertTrue(Files.size(path) > 0);
            }
        } finally {
            restoreFiles(files, backups);
        }
    }

    private Map<String, byte[]> backupFiles(String[] files) throws IOException {
        Map<String, byte[]> backups = new HashMap<>();

        for (String file : files) {
            Path path = Path.of(file);
            if (Files.exists(path)) {
                backups.put(file, Files.readAllBytes(path));
            }
        }

        return backups;
    }

    private void restoreFiles(String[] files, Map<String, byte[]> backups) throws IOException {
        for (String file : files) {
            Path path = Path.of(file);
            byte[] original = backups.get(file);

            if (original != null) {
                Files.write(path, original);
            } else {
                Files.deleteIfExists(path);
            }
        }
    }
}
