package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        try {
            return linked.deleteFirst();
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}