package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        modCount++;
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> curNode = head;
        int curIndex = 0;
        while (curIndex != index) {
            curNode = curNode.next;
            curIndex++;
        }
        return curNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> curNode = head;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkModification();
                return curNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                checkModification();
                E result = curNode.item;
                curNode = curNode.next;
                return result;
            }

            private void checkModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}