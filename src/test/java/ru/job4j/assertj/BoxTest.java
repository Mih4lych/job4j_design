package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .startsWith("S")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(-1, 10);
        String name = box.whatsThis();
        assertThat(name)
                .contains("object")
                .isEqualTo("Unknown object");
    }

    @Test
    void checkVertexForCube() {
        Box box = new Box(8, 10);
        assertThat(box.getNumberOfVertices())
                .isPositive()
                .isEqualTo(8);
    }

    @Test
    void checkVertexForUnknown() {
        Box box = new Box(8, -5);
        assertThat(box.getNumberOfVertices())
                .isNegative()
                .isEqualTo(-1);
    }

    @Test
    void isTetrahedronExists() {
        Box box = new Box(4, 2);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isUnknownExists() {
        Box box = new Box(-3, -5);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void checkAreaForCube() {
        Box box = new Box(8, 2);
        assertThat(box.getArea())
                .isPositive()
                .isGreaterThan(23)
                .isLessThan(25)
                .isEqualTo(24);
    }

    @Test
    void checkAreaForUnknown() {
        Box box = new Box(-5, 2);
        assertThat(box.getArea())
                .isNotInfinite()
                .isEqualTo(0);
    }
}