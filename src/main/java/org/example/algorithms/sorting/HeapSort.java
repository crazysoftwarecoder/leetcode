package org.example.algorithms.sorting;

import java.util.AbstractMap;
import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        for (int i=arr.length/2;i>=0;i--) {
            _heapify(arr, i, arr.length);
        }
        for (int i=arr.length-1;i>=0;i--) {
            swap(arr, 0, i);
            _heapify(arr, 0, i);
        }
    }

    private void _heapify(T[] arr, int index, int length) {
        int largest = index;
        int leftIndex = (index * 2) + 1;
        int rightIndex = (index * 2) + 2;

        if ((leftIndex < length) && (arr[leftIndex].compareTo(arr[largest])) > 0) {
            largest = leftIndex;
        }

        if ((rightIndex < length) && (arr[rightIndex].compareTo(arr[largest]) > 0)) {
            largest = rightIndex;
        }

        if (largest != index) {
            swap(arr, largest, index);
            _heapify(arr, largest, length);
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
