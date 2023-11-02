package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
                BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            StringBuilder intermediateResult = new StringBuilder();
            String data;
            String[] pair;

            while ((data = reader.readLine()) != null) {
                pair = data.split(" ");
                if (intermediateResult.isEmpty()) {
                    intermediateResult.append("400".equals(pair[0]) || "500".equals(pair[0]) ? pair[1] + ";" : "");
                } else {
                    if (!("400".equals(pair[0]) || "500".equals(pair[0]))) {
                        intermediateResult.append(pair[1]).append(";");
                        writer.write(intermediateResult + System.lineSeparator());
                        intermediateResult.setLength(0);
                    }
                }
            }

            if (intermediateResult.length() > 0) {
                writer.write(intermediateResult.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}