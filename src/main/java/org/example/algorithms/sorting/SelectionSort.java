package org.example.algorithms.sorting;

public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(T[] arr) {
        if (arr.length < 2) return;
        for (int i=0;i<arr.length-1;i++) {
            int lowestIndex = i;
            for (int j=i+1;j<arr.length;j++) {
                if ( arr[j].compareTo(arr[lowestIndex]) < 0 ) {
                    lowestIndex = j;
                }
            }
            if (i != lowestIndex) {
                swap(arr, i, lowestIndex);
            }
        }
    }

    private void swap(T[] arr, int src, int dest) {
        T tmp = arr[src];
        arr[src] = arr[dest];
        arr[dest] = tmp;
    }
}
