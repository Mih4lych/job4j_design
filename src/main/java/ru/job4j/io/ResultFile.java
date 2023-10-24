package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write("Hello, world!".getBytes());
            out.write(System.lineSeparator().getBytes());

            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 10; j++) {
                    out.write((i + " * " + j + " = " + (i * j)).getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}