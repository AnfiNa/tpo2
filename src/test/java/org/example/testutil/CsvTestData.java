package org.example.testutil;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CsvTestData {

    private CsvTestData() {
    }

    public static List<Row> load(String resourcePath) {
        try (var stream = CsvTestData.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            List<String> lines = new ArrayList<>(
                    new String(stream.readAllBytes(), StandardCharsets.UTF_8)
                            .replace("\r\n", "\n")
                            .lines()
                            .toList()
            );

            if (lines.isEmpty()) {
                return List.of();
            }

            String[] headers = split(lines.get(0));
            List<Row> rows = new ArrayList<>();

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] values = split(line);
                Map<String, String> data = new LinkedHashMap<>();

                for (int j = 0; j < headers.length; j++) {
                    String value = j < values.length ? values[j].trim() : "";
                    data.put(headers[j].trim(), value);
                }

                rows.add(new Row(data));
            }

            return rows;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static String[] split(String line) {
        return line.split(";", -1);
    }

    public record Row(Map<String, String> data) {

        public String getString(String column) {
            return data.get(column);
        }

        public double getDouble(String column) {
            String value = getString(column);
            if ("NaN".equalsIgnoreCase(value)) {
                return Double.NaN;
            }
            if ("Infinity".equalsIgnoreCase(value)) {
                return Double.POSITIVE_INFINITY;
            }
            if ("-Infinity".equalsIgnoreCase(value)) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.parseDouble(value);
        }
    }
}
