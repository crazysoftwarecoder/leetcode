package org.example.algorithms.sorting;

public interface Sorter<T extends Comparable<T>> {

    void sort(T[] arr);
}
