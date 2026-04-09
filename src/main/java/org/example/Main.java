package org.example;

import org.example.math.*;

public class Main {

    private static final double EPS = 1e-6;
    private static final double STEP = 0.1;
    private static final String OUTPUT_DIR = "csv-output";

    public static void main(String[] args) throws Exception {
        AbstractFunction sin = new Sin();
        AbstractFunction cos = new Cos(sin);
        AbstractFunction tan = new Tan(sin, cos);
        AbstractFunction sec = new Sec(cos);
        AbstractFunction csc = new Csc(sin);

        AbstractFunction ln = new Ln();
        AbstractFunction log2 = new Log2(ln);
        AbstractFunction log3 = new Log3(ln);
        AbstractFunction log10 = new Log10(ln);

        AbstractFunction system = new Function(
                sin, cos, tan, sec, csc,
                ln, log2, log3, log10
        );

        CsvExporter exporter = new CsvExporter();

        exporter.export("sin", sin, -Math.PI, Math.PI, STEP, EPS, outputFile("sin.csv"), ";");
        exporter.export("cos", cos, -Math.PI, Math.PI, STEP, EPS, outputFile("cos.csv"), ";");
        exporter.export("tan", tan, -Math.PI / 2 + STEP, Math.PI / 2 - STEP, STEP, EPS, outputFile("tan.csv"), ";");
        exporter.export("sec", sec, -Math.PI / 2 + STEP, Math.PI / 2 - STEP, STEP, EPS, outputFile("sec.csv"), ";");
        exporter.export("csc", csc, -Math.PI + STEP, Math.PI - STEP, STEP, EPS, outputFile("csc.csv"), ";");

        exporter.export("ln", ln, STEP, 10.0, STEP, EPS, outputFile("ln.csv"), ";");
        exporter.export("log2", log2, STEP, 10.0, STEP, EPS, outputFile("log2.csv"), ";");
        exporter.export("log3", log3, STEP, 10.0, STEP, EPS, outputFile("log3.csv"), ";");
        exporter.export("log10", log10, STEP, 10.0, STEP, EPS, outputFile("log10.csv"), ";");

        exporter.export("system", system, -5.0, 5.0, STEP, EPS, outputFile("system.csv"), ";");
    }

    private static String outputFile(String fileName) {
        return OUTPUT_DIR + "/" + fileName;
    }
}
