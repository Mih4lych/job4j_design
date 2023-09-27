package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkMissingEqual() {
        NameLoad nameLoad = new NameLoad();
        String wrongName = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(wrongName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol")
                .hasMessageContaining(wrongName);
    }

    @Test
    void checkMissingKey() {
        NameLoad nameLoad = new NameLoad();
        String wrongName = "=value";
        assertThatThrownBy(() -> nameLoad.parse(wrongName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(wrongName);
    }

    @Test
    void checkMissingValue() {
        NameLoad nameLoad = new NameLoad();
        String wrongName = "key=";
        assertThatThrownBy(() -> nameLoad.parse(wrongName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(wrongName);
    }
}