package org.example.dynamicprogramming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CountingBitsTest {

    private CountingBits obj;

    @BeforeEach
    public void setup() {
        obj = new CountingBits();
    }

    @Test
    public void testCountingBitsWithZero() {
        assertArrayEquals(new int[]{0}, obj.countingBits(0));
    }

    @Test
    public void testCountingBitsWithOne() {
        assertArrayEquals(new int[]{0, 1}, obj.countingBits(1));
    }

    @Test
    public void testCountingBitsWith10() {
        assertArrayEquals(new int[]{
                0,
                1,
                1,
                2,
                1,
                2,
                2,
                3,
                1,
                2,
                2
        }, obj.countingBits(10));
    }
}
