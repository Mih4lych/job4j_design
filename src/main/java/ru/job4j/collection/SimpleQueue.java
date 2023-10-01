package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int inSize;
    private int outSize;

    public T poll() {
        if (inSize == 0 && outSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outSize == 0) {
            swapStacks();
        }
        outSize--;
        return out.pop();
    }

    private void swapStacks() {
        while (inSize != 0) {
            T value = in.pop();
            out.push(value);
            inSize--;
            outSize++;
        }
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}