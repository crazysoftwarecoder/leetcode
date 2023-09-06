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
        mergeSort(arr, 0, arr.length-1);
    }

    private void mergeSort(T[] arr, int lo, int hiExclusive) {
        if (lo < hiExclusive) {
            int mid = (lo + hiExclusive) >>> 1;
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hiExclusive);

            merge(arr, lo, mid, hiExclusive);
        }
    }

    private void merge(T[] arr, int lo, int mid, int hi) {
        T[] arr1 = (T[]) Array.newInstance(clazz, (mid-lo) + 1);
        T[] arr2 = (T[]) Array.newInstance(clazz, (hi-mid));

        for (int i=0;i<arr1.length;i++) {
            arr1[i] = arr[lo+i];
        }

        for (int j=0;j<arr2.length;j++) {
            arr2[j] = arr[mid+1+j];
        }

        int i = 0;
        int j = 0;
        int k = lo;

        while (i<arr1.length && j<arr2.length) {
            if (arr1[i].compareTo(arr2[j]) <= 0) {
                arr[k++] = arr1[i++];
            }
            else {
                arr[k++] = arr2[j++];
            }
        }

        while (i<arr1.length) {
            arr[k++] = arr1[i++];
        }

        while (j<arr2.length) {
            arr[k++] = arr2[j++];
        }
    }
}
