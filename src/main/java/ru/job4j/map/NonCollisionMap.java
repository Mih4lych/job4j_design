package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }

        boolean result = putInTable(table, key, value);

        if (result) {
            count++;
            modCount++;
        }
        return result;
    }

    private boolean putInTable(MapEntry[] tableForInsert, K key, V value) {
        int index = indexFor(hash(Objects.hashCode(key)));

        boolean result = tableForInsert[index] == null;

        if (result) {
            tableForInsert[index] = new MapEntry<>(key, value);
        }

        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                putInTable(newTable, entry.key, entry.value);
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int keyHashCode = Objects.hashCode(key);
        int index = indexFor(hash(keyHashCode));
        MapEntry<K, V> entry = table[index];

        return checkEntry(index, key) ? entry.value : null;
    }

    @Override
    public boolean remove(K key) {
        int keyHashCode = Objects.hashCode(key);
        int index = indexFor(hash(keyHashCode));
        boolean result = checkEntry(index, key);

        if (result) {
            table[index] = null;
            count--;
            modCount++;
        }

        return result;
    }

    private boolean checkEntry(int index, K key) {
        MapEntry<K, V> entry = table[index];
        return entry != null && Objects.hashCode(key) == Objects.hashCode(entry.key) && Objects.equals(key, entry.key);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}