package org.example.algorithms.sorting;

import java.util.Arrays;

public class QuickSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(T[] arr) {
        _sort(arr, 0, arr.length-1);
    }

    private void _sort(T[] arr, int lo, int hi) {
        if (lo < hi) {
            int pivotIndex = partition(arr, lo, hi);
            _sort(arr, lo, pivotIndex-1);
            _sort(arr, pivotIndex+1, hi);
        }
    }

    private int partition(T[] arr, int lo, int hi) {
        int mid = (lo + hi) >>> 1;
        T pivotVal = arr[mid];
        swap(arr, mid, hi); // move pivot to end
        int pivotLoc = lo;

        for (int i=lo;i<hi;i++) {
            if (arr[i].compareTo(pivotVal) < 0) {
                swap(arr, i, pivotLoc); // Swap the value with pivotLoc
                pivotLoc++; // increment pivotLocation to the next.
            }
        }
        swap(arr, pivotLoc, hi);
        return pivotLoc;
    }

    public static void main(String[] args) {
        var qSort = new QuickSort<Integer>();
        Integer[] intArr = new Integer[] {10, 7, 8, 9, 1, 5};
        qSort.sort(intArr);
        System.out.println(Arrays.toString(intArr));
    }
}
