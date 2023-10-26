package ru.job4j.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LogFilterTest {

    private LogFilter logFilter;
    private List<String> result;

    @BeforeEach
    public void setUp() {
        logFilter = new LogFilter("data/log.txt");
        result = List.of("0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113",
                "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /job4j.ru/profile/ HTTP/1.1\" 404 1110",
                "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /job4j.ru/profileNew/ HTTP/1.1\" 404 -"
        );
    }

    @Test
    public void checkFilter() {
        assertThat(logFilter.filter()).hasSameElementsAs(result);
    }

    @Test
    public void checkSaveTo() {
        logFilter.saveTo("data/404.txt");
        try (BufferedReader in = new BufferedReader(new FileReader("data/404.txt"))) {
            List<String> resultForCheck = in
                    .lines()
                    .filter(s -> {
                        String[] strs = s.split(" ");

                        return "404".equals(strs[strs.length - 2]);
                    })
                    .toList();
            assertThat(resultForCheck).hasSameElementsAs(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(logFilter.filter()).hasSameElementsAs(result);
    }
}