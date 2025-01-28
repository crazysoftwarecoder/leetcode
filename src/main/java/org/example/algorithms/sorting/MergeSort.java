package org.example.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements Sorter<T> {

    private Class<T> clazz;

    public MergeSort(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void sort(T[] arr) {
        if (arr.length < 2) return;

        int mid = arr.length >>> 1;

        T[] left = Arrays.copyOfRange(arr, 0, mid);
        T[] right = Arrays.copyOfRange(arr, mid, arr.length);

        sort(left);
        sort(right);

        merge(arr, left, right);
    }

    private void merge(T[] arr, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;

        while ( (i < left.length) && (j < right.length) ) {
            if (left[i].compareTo(right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
