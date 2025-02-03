package org.example.algorithms.twopointers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrappingRainWater42HardTest {
    private TrappingRainWater42Hard solution;

    @BeforeEach
    public void setUp() {
        solution = new TrappingRainWater42Hard();
    }

    @Test
    public void testEmptyArray() {
        int[] height = {};
        assertEquals(0, solution.trap(height));
    }

    @Test
    public void testSingleElement() {
        int[] height = {1};
        assertEquals(0, solution.trap(height));
    }

    @Test
    public void testNoWater() {
        int[] height = {1, 2, 3, 4, 5};
        assertEquals(0, solution.trap(height));
    }

    @Test
    public void testBasicCase() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        assertEquals(6, solution.trap(height));
    }

    @Test
    public void testSymmetricCase() {
        int[] height = {3, 0, 0, 3};
        assertEquals(6, solution.trap(height));
    }

    @Test
    public void testDescendingHeights() {
        int[] height = {5, 4, 3, 2, 1};
        assertEquals(0, solution.trap(height));
    }

    @Test
    public void testAscendingHeights() {
        int[] height = {1, 2, 3, 4, 5};
        assertEquals(0, solution.trap(height));
    }

}
