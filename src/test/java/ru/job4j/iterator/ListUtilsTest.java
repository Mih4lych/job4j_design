package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenAddAfterOutOfRange() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfWithMatch() {
        ListUtils.removeIf(input, i -> i > 1);
        assertThat(input).containsOnly(1);
    }

    @Test
    void whenRemoveIfWithoutMatch() {
        ListUtils.removeIf(input, i -> i > 3);
        assertThat(input).containsOnly(1, 3);
    }

    @Test
    void whenReplaceIfWithMatch() {
        ListUtils.replaceIf(input, i -> i > 1, 1);
        assertThat(input).hasSize(2).containsOnly(1);
    }

    @Test
    void whenReplaceIfWithoutMatch() {
        ListUtils.replaceIf(input, i -> i > 3, 1);
        assertThat(input).containsOnly(1, 3);
    }

    @Test
    void whenRemoveAllWithMatch() {
        ListUtils.removeAll(input, List.of(3));
        assertThat(input).hasSize(1).containsOnly(1);
    }

    @Test
    void whenRemoveAllWithoutMatch() {
        ListUtils.removeAll(input, List.of(4));
        assertThat(input).containsOnly(1, 3);
    }

    @Test
    void whenRemoveAllWithItself() {
        ListUtils.removeAll(input, input);
        assertThat(input).hasSize(0);
    }
}