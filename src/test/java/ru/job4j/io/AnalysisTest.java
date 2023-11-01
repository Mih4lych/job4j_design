package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    public void checkFirstExample() {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/first_example.log", "data/first_example.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader("data/first_example.csv"))) {
            List<String> result = reader.lines().toList();
            assertThat(result).hasSameElementsAs(List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkSecondExample() {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/second_example.log", "data/second_example.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader("data/second_example.csv"))) {
            List<String> result = reader.lines().toList();
            assertThat(result).hasSameElementsAs(List.of("10:57:01;11:02:02;"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkNotStoppingExample() {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/not_stopping_example.log", "data/not_stopping_example.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader("data/not_stopping_example.csv"))) {
            List<String> result = reader.lines().toList();
            assertThat(result).isEmpty();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkNotRunningExample() {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/not_running_example.log", "data/not_running_example.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader("data/not_running_example.csv"))) {
            List<String> result = reader.lines().toList();
            assertThat(result).hasSameElementsAs(List.of("10:57:01;"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}