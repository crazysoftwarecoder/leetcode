package org.example.algorithms.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class SortingTest {

    private static Random random;

    private static final Integer NUMBER_OF_ELEMENTS = 10000;

    @BeforeAll
    public static void setup() {
        random = new Random();
    }

    @ParameterizedTest
    @MethodSource("getSorters")
    public void sortTest(Sorter<Integer> sorter) {
        var arr = generateRandomArray(NUMBER_OF_ELEMENTS);
        var sortedArray = copyArray(arr);
        Arrays.sort(sortedArray);
        long currentTimeMillis = System.currentTimeMillis();
        sorter.sort(arr);
        System.out.println("Sorter : "
                + sorter.getClass().getSimpleName() + " took "
                + ((System.currentTimeMillis() - currentTimeMillis) / 1000)
                + " secs" );
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

    private static Stream<Sorter<Integer>> getSorters() {
        return Stream.of(new BubbleSort<>(), new SelectionSort<>(), new InsertionSort<>());
    }
}
