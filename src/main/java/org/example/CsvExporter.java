package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class CsvExporter {

    public CsvExporter() {
    }

    public void export(String moduleName,
                       AbstractFunction module,
                       double start,
                       double end,
                       double step,
                       double epsilon,
                       String fileName,
                       String delimiter) throws IOException {

        validateRange(start, end, step);
        prepareParentDirectory(fileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("X" + delimiter + "Result of " + moduleName + "(X)");

            for (double x = start; x <= end + epsilon; x += step) {
                double y = module.calculate(x, epsilon);

                writer.println(
                        String.format(
                                Locale.US,
                                "%.10f%s%.10f",
                                x,
                                delimiter,
                                y
                        )
                );
            }
        }
    }

    public void export(AbstractFunction module,
                       double start,
                       double end,
                       double step,
                       double epsilon,
                       String fileName,
                       String delimiter) throws IOException {
        export("module", module, start, end, step, epsilon, fileName, delimiter);
    }

    private void validateRange(double start, double end, double step) {
        if (step <= 0) {
            throw new IllegalArgumentException("Step must be positive");
        }
        if (start > end) {
            throw new IllegalArgumentException("Start must be less than or equal to end");
        }
    }

    private void prepareParentDirectory(String fileName) throws IOException {
        Path parent = Path.of(fileName).getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
    }
}
