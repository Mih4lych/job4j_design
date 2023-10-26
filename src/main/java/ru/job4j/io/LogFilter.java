package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/log.txt"))) {
            return in
                    .lines()
                    .filter(s -> {
                        String[] strs = s.split(" ");

                        return "404".equals(strs[strs.length - 2]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}