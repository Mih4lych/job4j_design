package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
                BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String data;
            String[] pair;
            boolean isStopped = false;

            while ((data = reader.readLine()) != null) {
                pair = data.split(" ");
                if (isStopped && !("400".equals(pair[0]) || "500".equals(pair[0]))) {
                    writer.write(pair[1] + ";" + System.lineSeparator());
                    isStopped = false;
                } else if (!isStopped && ("400".equals(pair[0]) || "500".equals(pair[0]))) {
                    writer.write(pair[1] + ";");
                    isStopped = true;
                }
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