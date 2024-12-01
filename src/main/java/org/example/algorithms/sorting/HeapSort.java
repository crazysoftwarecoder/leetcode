package org.example.algorithms.sorting;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        int n = arr.length;

        // Max heapify the array first.
        for (int i=n/2-1;i>=0;i--) {
            minHeapify(arr, n, i);
        }

        // move root to edge and max heapify
        for (int i=n-1;i>0;i--) {
            swap(arr, 0, i);
            minHeapify(arr, i, 0);
        }

    }

    private void minHeapify(T[] arr, int maxLen, int root) {
        int largest = root;
        int left = (root * 2) + 1;
        int right = (root * 2) + 2;

        if ( (left < maxLen) && (arr[left].compareTo(arr[largest]) < 0) ) {
            largest = left;
        }

        if ( (right < maxLen) && (arr[right].compareTo(arr[largest])) < 0) {
            largest = right;
        }

        if (largest != root) {
            swap(arr, largest, root);

            minHeapify(arr, maxLen, largest);
        }
    }


    @Override
    public void swap(T[] arr, int src, int dest) {
        Sorter.super.swap(arr, src, dest);
    }

    public static void main(String[] args) {
        var integerArr = new Integer[] {5,4,3,5,2,1};
        new HeapSort<Integer>().sort(integerArr);
        System.out.println(Arrays.toString(integerArr));
    }
}
