package org.example.algorithms.sorting;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        int n = arr.length;
        if (n < 2) return;
        for (int i=arr.length/2;i>=0;i--) {
            maxHeapify(arr, n, i);
        }

        for (int i=n-1;i>0;i--) {
            swap(arr, 0, i);

            maxHeapify(arr, i, 0);
        }
    }

    private void maxHeapify(T[] arr, int maxLen, int root) {

        int leftChild = 2 * root + 1;
        int rightChild = 2 * root + 2;

        int largest = root;

        if (leftChild < maxLen && arr[largest].compareTo(arr[leftChild]) <= 0) {
            largest = leftChild;
        }

        if (rightChild < maxLen && arr[largest].compareTo(arr[rightChild]) <= 0) {
            largest = rightChild;
        }

        if (root != largest) {
            swap(arr, root, largest);
            maxHeapify(arr, maxLen, largest);
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
