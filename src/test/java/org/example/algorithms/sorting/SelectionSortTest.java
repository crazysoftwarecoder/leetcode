package org.example.algorithms.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class SelectionSortTest {

    private static Random random;

    private static SelectionSort<Integer> selectionSort;

    @BeforeAll
    public static void setup() {
        random = new Random();
        selectionSort = new SelectionSort();
    }

    @Test
    public void selectionSortTest() {
        var arr = generateRandomArray(5);
        var sortedArray = copyArray(arr);
        Arrays.sort(sortedArray);
        selectionSort.sort(arr);
        Assertions.assertArrayEquals(arr, sortedArray);
    }

    private Integer[] copyArray(Integer[] incoming) {
        Integer[] res = new Integer[incoming.length];
        for (int i=0; i< incoming.length; i++) {
            res[i] = incoming[i];
        }
        return res;
    }

    private Integer[] generateRandomArray(int size) {
        Integer[] arr = new Integer[size];
        for (int i=0; i<arr.length; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }
}
