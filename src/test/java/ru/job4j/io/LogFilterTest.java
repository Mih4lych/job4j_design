package ru.job4j.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LogFilterTest {

    private LogFilter logFilter;

    @BeforeEach
    public void setUp() {
        logFilter = new LogFilter("data/log.txt");
    }

    @Test
    public void checkFilter() {
        List<String> result = List.of("0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113",
                "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /job4j.ru/profile/ HTTP/1.1\" 404 1110",
                "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /job4j.ru/profileNew/ HTTP/1.1\" 404 -"
        );
        assertThat(logFilter.filter()).hasSameElementsAs(result);
    }
}