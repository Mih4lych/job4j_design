package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
                BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            List<String> results = new LinkedList<>();
            StringBuilder intermediateResult = new StringBuilder();

            reader
                    .lines()
                    .map(s -> s.split(" "))
                    .forEach(pair -> {
                        if (intermediateResult.length() == 0) {
                            intermediateResult.append("400".equals(pair[0]) || "500".equals(pair[0]) ? pair[1] + ";" : "");
                        } else {
                            if (!("400".equals(pair[0]) || "500".equals(pair[0]))) {
                                intermediateResult.append(pair[1]).append(";");
                                results.add(intermediateResult.toString());
                                intermediateResult.setLength(0);
                            }
                        }
                    });

            if (intermediateResult.length() > 0) {
                results.add(intermediateResult.toString());
            }

            for (String s : results) {
                writer.write(s + System.lineSeparator());
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