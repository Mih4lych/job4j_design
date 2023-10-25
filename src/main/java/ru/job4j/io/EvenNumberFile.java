package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }

            Arrays.stream(text
                    .toString()
                    .split(System.lineSeparator()))
                    .map(Integer::parseInt)
                    .forEach(n -> System.out.println(n + " is " + (checkEven(n) ? "even" : "odd")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkEven(int num) {
        return num % 2 == 0;
    }
}