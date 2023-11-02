package ru.job4j.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    private Analysis analysis;

    @BeforeEach
    void initialize() {
        analysis = new Analysis();
    }

    @Test
    void checkFirstExample(@TempDir Path tempDir) {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target = tempDir.resolve("target.txt").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").hasToString(rsl.toString());
    }

    @Test
    void checkSecondExample(@TempDir Path tempDir) {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("300 11:02:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target = tempDir.resolve("target.txt").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat("10:57:01;11:02:02;").hasToString(rsl.toString());
    }

    @Test
    void checkNotStoppingExample(@TempDir Path tempDir) {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("300 10:59:01");
            out.println("200 11:02:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target = tempDir.resolve("target.txt").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat("").hasToString(rsl.toString());
    }

    @Test
    void checkNotRunningExample(@TempDir Path tempDir) {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 11:01:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target = tempDir.resolve("target.txt").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat("10:57:01;").hasToString(rsl.toString());
    }
}