package org.example;

import org.example.math.*;
import org.example.CsvExporter;

public class Main {

    public static void main(String[] args) throws Exception {

        double EPS = 1e-6;

        // Тригонометрия
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction tan = new Tan(sin, cos);
        AbstractFunction sec = new Sec(cos);
        AbstractFunction csc = new Csc(sin);

        // Логарифмы
        AbstractFunction ln = new Ln();
        AbstractFunction log2 = new Log2(ln);
        AbstractFunction log3 = new Log3(ln);
        AbstractFunction log10 = new Log10(ln);

        // Система
        AbstractFunction system = new Function(
                sin, cos, tan, sec, csc,
                ln, log2, log3, log10
        );

        CsvExporter exporter = new CsvExporter();

        // Примеры экспорта
        exporter.export(sin, -Math.PI, Math.PI, 0.1, EPS, "sin.csv", ";");
        exporter.export(ln, 0.1, 10.0, 0.1, EPS, "ln.csv", ";");
        exporter.export(system, -5.0, 5.0, 0.1, EPS, "system.csv", ";");
    }
}
