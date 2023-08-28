package org.example.algorithms.sorting;

public interface Sorter<T extends Comparable<T>> {

    void sort(T[] arr);

    default void swap(T[] arr, int src, int dest) {
        T tmp = arr[src];
        arr[src] = arr[dest];
        arr[dest] = tmp;
    }
}
