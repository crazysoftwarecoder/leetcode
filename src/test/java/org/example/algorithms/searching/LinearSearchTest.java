package org.example.algorithms.searching;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearSearchTest {

    private static LinearSearch linearSearch;

    @BeforeAll
    public static void setup() {
        linearSearch = new LinearSearch();
    }

    @Test
    public void testWithNullList() {
        assertEquals(-1, linearSearch.search(null, 0));
    }

    @Test
    public void testWithSingleElementListAndNullSearch() {
        assertEquals(-1, linearSearch.search(List.of(1), null));
    }

    @Test
    public void testWithSingleElementListAndItemExist() {
        assertEquals(0, linearSearch.search(List.of(1), 1));
    }

    @Test
    public void testWithSingleElementListAndItemDoesNotExist() {
        assertEquals(-1, linearSearch.search(List.of(1), 2));
    }

    @Test
    public void testWithMultipleElementListAndItemDoesExist() {
        assertEquals(1, linearSearch.search(List.of(1,2,3), 2));
    }

    @Test
    public void testWithMultipleElementListAndItemDoesNotExist() {
        assertEquals(-1, linearSearch.search(List.of(1,2,3), 4));
    }
}
