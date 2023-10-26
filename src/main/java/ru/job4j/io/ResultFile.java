package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/result.txt")
                ))) {
            out.println("Hello, world!");

            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 10; j++) {
                    out.println(i + " * " + j + " = " + (i * j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}