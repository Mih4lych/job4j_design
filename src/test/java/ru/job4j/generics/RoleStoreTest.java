package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsBoss() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("Boss");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        assertThat(store.findById("10")).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsBoss() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.add(new Role("1", "Secretary"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("Boss");
    }

    @Test
    void whenReplaceThenRoleNameIsSecretary() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.replace("1", new Role("1", "Secretary"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("Secretary");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.replace("10", new Role("10", "Secretary"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("Boss");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.delete("1");
        assertThat(store.findById("1")).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsBoss() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.delete("10");
        assertThat(store.findById("1").getRoleName()).isEqualTo("Boss");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        assertThat(store.replace("1", new Role("1", "Secretary"))).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        assertThat(store.replace("10", new Role("10", "Secretary"))).isFalse();
    }
}