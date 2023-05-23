package org.example.algorithms.searching;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchingAlgorithmsTest {

    @ParameterizedTest
    @MethodSource("provideValues")
    public void testWithNullList(Search search) {
        assertEquals(-1, search.search(null, 0));
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    public void testWithSingleElementListAndNullSearch(Search search) {
        assertEquals(-1, search.search(new ArrayList<>(List.of(1)), null));
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    public void testWithSingleElementListAndItemExist(Search search) {
        assertEquals(0, search.search(new ArrayList<>(List.of(1)), 1));
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    public void testWithSingleElementListAndItemDoesNotExist(Search search) {
        assertEquals(-1, search.search(new ArrayList<>(List.of(1)), 2));
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    public void testWithMultipleElementListAndItemDoesExist(Search search) {
        assertEquals(1, search.search(new ArrayList<>(List.of(1,2,3)), 2));
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    public void testWithMultipleElementListAndItemDoesNotExist(Search search) {
        assertEquals(-1, search.search(new ArrayList<>(List.of(1,2,3)), 4));
    }

    static Stream<Search> provideValues() {
        return Stream.<Search>of(new LinearSearch(), new BinarySearch(BinarySearch.TYPE.ITERATIVE), new BinarySearch(BinarySearch.TYPE.RECURSIVE));
    }
}
