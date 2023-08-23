package org.example.algorithms.sorting;

import java.util.Random;

public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(T[] arr) {
        for (int i=0;i<arr.length-1;i++) {
            boolean swapped = false;
            for (int j=1;j<arr.length;j++) {
                if (arr[j].compareTo(arr[j-1]) < 0) {
                    swap(arr, j, j-1);
                    swapped = true;
                }
            }
            if (!swapped) break; // bubble sort is complete if the entire array is in sorted order.
        }
    }

    private void swap(T[] arr, int src, int dest) {
        T tmp = arr[dest];
        arr[dest] = arr[src];
        arr[src] = tmp;
    }
}
