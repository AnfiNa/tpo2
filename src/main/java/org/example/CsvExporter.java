package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class CsvExporter {

    public CsvExporter() {
    }

    public void export(AbstractFunction module,
                       double start,
                       double end,
                       double step,
                       double epsilon,
                       String fileName,
                       String delimiter) throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            // Заголовок
            writer.println("X" + delimiter + "Result");

            // Генерация значений
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
}
