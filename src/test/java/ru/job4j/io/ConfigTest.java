package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("order")).isEqualTo("10");
        assertThat(config.value("color")).isNull();
    }

    @Test
    void whenPairWithEmptyLines() {
        String path = "./data/pair_with_empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("order")).isEqualTo("10");
        assertThat(config.value("color")).isEqualTo("blue");
        assertThat(config.value("   ")).isNull();
    }

    @Test
    void whenWrongPairWithEmptyValue() {
        String path = "./data/pair_with_empty_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongPairWithEmptyKey() {
        String path = "./data/pair_with_empty_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongPairWithSeveralEquals() {
        String path = "./data/pair_with_several_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("order")).isEqualTo("10=");
    }

}