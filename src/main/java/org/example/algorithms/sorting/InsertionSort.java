package org.example.algorithms.sorting;

import java.util.Arrays;

public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(T[] arr) {
        for (int i=1;i<arr.length;i++) {
            int j = i;
            while (j>0) {
                if (arr[j].compareTo(arr[j-1]) < 0) {
                    swap(arr, j, j-1);
                    j--;
                }
                else {
                    break;
                }
            }
        }
    }
}
